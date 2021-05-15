package ch.ffhs.fac.flang.javafx;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.nio.file.Files;

import ch.ffhs.fac.flang.parser.Parser;
import ch.ffhs.fac.flang.parser.Scanner;
import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.std.ArrayCreate;
import ch.ffhs.fac.flang.runtime.std.ArrayFilter;
import ch.ffhs.fac.flang.runtime.std.ArrayGet;
import ch.ffhs.fac.flang.runtime.std.ArrayMap;
import ch.ffhs.fac.flang.runtime.std.ArraySet;
import ch.ffhs.fac.flang.runtime.std.CastDecimal;
import ch.ffhs.fac.flang.runtime.std.CastString;
import ch.ffhs.fac.flang.runtime.std.Print;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class App extends Application {
	private static final String mainWindowFXML = "/simple-ide.fxml";
	private static final String aboutWindowFXML = "/simple-ide-about.fxml";
	private Stage mainWindow;

	@FXML
	private TextArea textareaInput;

	@FXML
	private TextArea textareaOutput;

	public static void launchJavaFX(final String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		var cl = getClass();
		var location = cl.getResource(mainWindowFXML);
		FXMLLoader loader = new FXMLLoader(location);
		loader.setController(this);
		try {
			mainWindow = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mainWindow.show();
	}

	@FXML
	private void open(ActionEvent event) {
		try {
			final var fileChooser = new FileChooser();
			fileChooser.setTitle("Open FLang File");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("FLang Files", "*.flang"));
			final var file = fileChooser.showOpenDialog(mainWindow);
			if (file != null) {
				textareaInput.clear();
				textareaInput.setText(Files.readString(file.toPath()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void exit(ActionEvent event) {
		System.exit(0);
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
		textareaOutput.clear();

		final var sourceCode = textareaInput.getText();
		final var reader = new StringReader(sourceCode);
		final var lexer = new Scanner(reader);
		final var parser = new Parser(lexer);

		try {
			final var symbol = parser.parse();
			textareaOutput.appendText("successfully parsed\n");

			final var document = (Document) symbol.value;
			document.declareFunction(CastString.NAME, new CastString());
			document.declareFunction(CastDecimal.NAME, new CastDecimal());
			document.declareFunction(ArrayCreate.NAME, new ArrayCreate());
			document.declareFunction(ArrayGet.NAME, new ArrayGet());
			document.declareFunction(ArraySet.NAME, new ArraySet());
			document.declareFunction(ArrayMap.NAME, new ArrayMap());
			document.declareFunction(ArrayFilter.NAME, new ArrayFilter());
			document.declareFunction(Print.NAME, new Print(new Writer() {
				private boolean flushed = true;

				@Override
				public void write(char[] cbuf, int off, int len) throws IOException {
					if (flushed) {
						flushed = false;
					} else {
						textareaOutput.appendText(" ");
					}

					textareaOutput.appendText(new String(cbuf, off, len));
				}

				@Override
				public void flush() throws IOException {
					flushed = true;
					textareaOutput.appendText("\n");
				}

				@Override
				public void close() throws IOException {
				}
			}));
			final var returnValue = document.execute();
			textareaOutput.appendText(
					"Programm finished with return value: " + returnValue.toString(document) + "\n");
		} catch (Throwable e) {
			textareaOutput.setText(e.getMessage());
			e.printStackTrace();
		}

		/*
		 * Old code which prints out the tokens
		 * try {
		 * while(!lexer.yyatEOF()) {
		 * final var sym = lexer.next_token();
		 * 
		 * textareaOutput.appendText("State:      " + lexer.yystate() + "\n");
		 * textareaOutput.appendText("Match:      " + lexer.yytext() + "\n");
		 * textareaOutput.appendText("Nummeric:   " + sym + "\n");
		 * textareaOutput.appendText("Value:      " + sym.value + "\n");
		 * textareaOutput.appendText("Line:       " + sym.left + "\n");
		 * textareaOutput.appendText("Column:     " + sym.right + "\n");
		 * textareaOutput.appendText("\n");
		 * }
		 * 
		 * lexer.yyclose();
		 * } catch (Exception e) {
		 * textareaOutput.setText(e.getMessage());
		 * e.printStackTrace();
		 * }
		 */
	}
}
