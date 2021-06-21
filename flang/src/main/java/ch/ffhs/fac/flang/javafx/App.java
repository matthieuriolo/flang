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
import java.util.concurrent.Future;

import ch.ffhs.fac.flang.parser.Parser;
import ch.ffhs.fac.flang.parser.Scanner;
import ch.ffhs.fac.flang.parser.interfaces.Literal;
import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.std.ArrayCreate;
import ch.ffhs.fac.flang.runtime.std.ArrayFilter;
import ch.ffhs.fac.flang.runtime.std.ArrayGet;
import ch.ffhs.fac.flang.runtime.std.ArrayMap;
import ch.ffhs.fac.flang.runtime.std.ArraySet;
import ch.ffhs.fac.flang.runtime.std.CastDecimal;
import ch.ffhs.fac.flang.runtime.std.CastString;
import ch.ffhs.fac.flang.runtime.std.Print;
import ch.ffhs.fac.flang.runtime.std.Read;
import ch.ffhs.fac.flang.runtime.visitors.ASTStringBuilder;
import ch.ffhs.fac.flang.runtime.visitors.Validator;
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
			e.printStackTrace();
		}
	}

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
	}

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

	@FXML
	private void clickCompile(ActionEvent event) {
		if (documentTask != null && !documentTask.isDone()) {
			documentTask.cancel(true);
		}
		textareaOutput.clear();
		textareaToken.clear();

		documentTask = new Task<Literal>() {
			@Override
			protected Literal call() throws Exception {
				final var sourceCode = textareaCode.getText();
				final var reader = new StringReader(sourceCode);
				final var lexer = new Scanner(reader);
				@SuppressWarnings("deprecation")
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

				final var symbol = parser.parse();
				final var document = (Document) symbol.value;
				document.declareFunction(CastString.NAME, new CastString());
				document.declareFunction(CastDecimal.NAME, new CastDecimal());
				document.declareFunction(ArrayCreate.NAME, new ArrayCreate());
				document.declareFunction(ArrayGet.NAME, new ArrayGet());
				document.declareFunction(ArraySet.NAME, new ArraySet());
				document.declareFunction(ArrayMap.NAME, new ArrayMap());
				document.declareFunction(ArrayFilter.NAME, new ArrayFilter());
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
					public void close() throws IOException {
					}
				}));

				try (PipedReader userReader = new PipedReader();
						PipedWriter userWriter = new PipedWriter(userReader)) {
					buttonInput.setOnMouseClicked(new EventHandler<Event>() {
						@Override
						public void handle(Event event) {
							try {
								userWriter.write(textfieldInput.getText() + "\n");
							} catch (IOException e) {
								throw new RuntimeException(e);
							}
						}
					});
					document.declareFunction(Read.NAME, new Read(new BufferedReader(userReader)));
					
					final var astStr = new ASTStringBuilder();
					astStr.visitDocument(document);
					textareaAST.setText(astStr.getString());
					
					final var validator = new Validator();
					validator.visitDocument(document);
					
					return document.execute();
				} catch (Throwable e) {
					throw new Exception(e);
				}
			}
		};

		documentTask.setOnFailed((evt) -> {
			textareaOutput.appendText("There has been an error\n");
			StringWriter traceWriter = new StringWriter();
			documentTask.getException().printStackTrace(new PrintWriter(traceWriter));
			textareaOutput.appendText(documentTask.getException().toString() + "\n" + traceWriter.toString());
		});

		documentTask.setOnSucceeded((evt) -> {
			textareaOutput
					.appendText("Programm finished with return value: " + documentTask.getValue().toString() + "\n");
		});

		final var executor = Executors.newSingleThreadExecutor();
		executor.execute(documentTask);
		executor.shutdown();
	}
}
