package ch.ffhs.fac.flang.javafx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.concurrent.Executors;

import ch.ffhs.fac.flang.parser.Parser;
import ch.ffhs.fac.flang.parser.Scanner;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.std.ArrayFilter;
import ch.ffhs.fac.flang.runtime.std.ArrayMap;
import ch.ffhs.fac.flang.runtime.std.CastDecimal;
import ch.ffhs.fac.flang.runtime.std.CastString;
import ch.ffhs.fac.flang.runtime.std.Print;
import ch.ffhs.fac.flang.runtime.std.Read;
import ch.ffhs.fac.flang.runtime.visitors.ASTStringBuilder;
import java_cup.runtime.Symbol;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Application instance for JavaFX and UI controller for the stage "simple-ide.fxml"
 * @author matthieuriolo
 */
public class App extends Application {
	private static final String mainWindowFXML = "/simple-ide.fxml";
	private static final String aboutWindowFXML = "/simple-ide-about.fxml";
	private Stage mainWindow;
	private Task<Literal> documentTask;

	@FXML
	private TextArea textareaCode;

	@FXML
	private TextArea textareaOutput;

	@FXML
	private TextArea textareaToken;

	@FXML
	private TextArea textareaAST;

	@FXML
	private TextField textfieldInput;

	@FXML
	private Button buttonInput;
	
	@FXML
	private Button buttonExecute;
	
	@FXML
	private Button buttonCancel;
	
	private BufferedReader functionReadReader;
	private PipedWriter functionReadWriter;

	/**
	 * Launches the JavaFX Application
	 * @param args
	 */
	public static void launchJavaFX(final String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			var cl = getClass();
			var location = cl.getResource(mainWindowFXML);
			FXMLLoader loader = new FXMLLoader(location);
			loader.setController(this);
			mainWindow = loader.load();
			mainWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handler for the menu entry "open". Display a file dialog and inserts the selected file into the editor
	 * @param event
	 */
	@FXML
	private void open(ActionEvent event) {
		try {
			final var fileChooser = new FileChooser();
			fileChooser.setTitle("Open FLang File");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FLang Files", "*.flang"));
			final var file = fileChooser.showOpenDialog(mainWindow);
			if (file != null) {
				textareaCode.clear();
				textareaCode.setText(Files.readString(file.toPath()));
			}
		} catch (IOException e) {
			addExceptionToOutput(e);
		}
	}

	/**
	 * Handler for the menu item "Quit". Closes the application
	 * @param event
	 */
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}

	/**
	 * Handler for the menu item "About". Displays a model with the information about the application
	 * @param event
	 */
	@FXML
	private void showAbout(ActionEvent event) {
		final var cl = getClass();
		final var location = cl.getResource(aboutWindowFXML);
		final var loader = new FXMLLoader(location);
		try {
			final Stage aboutWindow = loader.load();
			aboutWindow.initModality(Modality.WINDOW_MODAL);
			aboutWindow.initOwner(mainWindow);
			aboutWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Handler for the button "cancel". Prevents the thread from further executing the parsed code
	 * @param event
	 */
	@FXML
	private void clickCancel(ActionEvent event) {
		if (documentTask != null && !documentTask.isDone()) {
			documentTask.cancel(true);
		}
		
		resetParserEnvironment();
	}

	/**
	 * Handler for the button "execute". Parses the content from the editor and executes the code in a separate thread
	 * @param event
	 */
	@FXML
	private void clickExecute(ActionEvent event) {
		textareaOutput.clear();
		textareaToken.clear();
		buttonExecute.setDisable(true);
		buttonCancel.setDisable(false);
		buttonInput.setDisable(false);
		
		documentTask = new Task<Literal>() {
			@Override
			protected Literal call() throws Exception {
				final var document = parse();
				initializeDocument(document);
				
				final var astStr = new ASTStringBuilder();
				astStr.visitDocument(document);
				textareaAST.setText(astStr.getString());
				
				try {
					document.validate();
					return document.execute();
				}catch(Throwable e) {
					throw new Exception(e);
				}
			}
		};

		documentTask.setOnFailed((evt) -> {
			resetParserEnvironment();
			addExceptionToOutput(documentTask.getException());
		});

		documentTask.setOnSucceeded((evt) -> {
			resetParserEnvironment();
			textareaOutput
					.appendText("Programm finished with return value: " + documentTask.getValue().toString() + "\n");
		});

		final var executor = Executors.newSingleThreadExecutor();
		executor.execute(documentTask);
		executor.shutdown();
	}
	
	/**
	 * Resets the GUI and the pipes for a new execution
	 */
	private void resetParserEnvironment() {
		buttonExecute.setDisable(false);
		buttonCancel.setDisable(true);
		buttonInput.setDisable(true);
		if(functionReadReader != null) {
			try {
				functionReadReader.close();
				functionReadReader = null;
			}catch(Throwable e) {
				addExceptionToOutput(e);
			}
		}
		
		if(functionReadWriter != null) {
			try {
				functionReadWriter.close();
				functionReadWriter = null;
			}catch(Throwable e) {
				addExceptionToOutput(e);
			}
		}
	}
	
	/**
	 * Constructs an document from the textarea by using the parser
	 * @return
	 * @throws Exception
	 */
	private Document parse() throws Exception {
		final var sourceCode = textareaCode.getText();
		final var reader = new StringReader(sourceCode);
		final var lexer = new Scanner(reader);
		@SuppressWarnings("deprecation")
		// implement lexer observer
		final var parser = new Parser(lexer) {
			@Override
			public Symbol scan() throws Exception {
				final var sym = super.scan();
				Platform.runLater(() -> {
					textareaToken.appendText("State:      " + lexer.yystate() + "\n");
					textareaToken.appendText("Match:      " + lexer.yytext() + "\n");
					textareaToken.appendText("Nummeric:   " + sym + "\n");
					textareaToken.appendText("Value:      " + sym.value + "\n");
					textareaToken.appendText("Line:       " + sym.left + "\n");
					textareaToken.appendText("Column:     " + sym.right + "\n");
					textareaToken.appendText("\n");
				});
				return sym;
			}
		};
		
		return parser.parseDocument();
	}
	
	/**
	 * Adds some default functions to the document class
	 * @param document
	 * @throws Exception
	 */
	private void initializeDocument(final Document document) throws Exception {
		// add some default functions
		document.declareFunction(CastString.NAME, new CastString());
		document.declareFunction(CastDecimal.NAME, new CastDecimal());
		document.declareFunction(ArrayMap.NAME, new ArrayMap());
		document.declareFunction(ArrayFilter.NAME, new ArrayFilter());
		
		// add function "print"
		document.declareFunction(Print.NAME, new Print(new Writer() {
			private StringBuffer buffer = new StringBuffer();

			@Override
			public void write(char[] cbuf, int off, int len) throws IOException {
				if (buffer.length() > 0) {
					buffer.append(" ");
				}

				buffer.append(new String(cbuf, off, len));
			}

			@Override
			public void flush() throws IOException {
				buffer.append("\n");
				final var str = buffer.toString();
				buffer = new StringBuffer();
				Platform.runLater(() -> textareaOutput.appendText(str));
			}

			@Override
			public void close() throws IOException {}
		}));

		// add function "read"
		final var userReader = new PipedReader();
		functionReadWriter = new PipedWriter(userReader);
		buttonInput.setOnMouseClicked(new EventHandler<Event>() {
			@Override
			public void handle(Event event) {
				try {
					functionReadWriter.write(textfieldInput.getText() + "\n");
				} catch (IOException e) {
					addExceptionToOutput(e);
				}
			}
		});
		functionReadReader = new BufferedReader(userReader);
		document.declareFunction(Read.NAME, new Read(functionReadReader));
	}
	
	/**
	 * Adds an exception to the output text area
	 * @param e
	 */
	private void addExceptionToOutput(final Throwable e) {
		try(final var traceWriter = new StringWriter();
				final var printWriter = new PrintWriter(traceWriter)) {
			e.printStackTrace(printWriter);
			Platform.runLater(() -> textareaOutput.appendText(e.toString() + "\n" + traceWriter.toString()));	
		}catch(Throwable error) {
			error.printStackTrace();
		}
	}
}
