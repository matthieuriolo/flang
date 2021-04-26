package ch.ffhs.fac.flang.javafx;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;

import ch.ffhs.fac.flang.parser.Parser;
import ch.ffhs.fac.flang.parser.Scanner;
import ch.ffhs.fac.flang.runtime.Document;
import ch.ffhs.fac.flang.runtime.std.PrintFunction;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class App extends Application {
	final String mainWindowFXML = "/simple-ide.fxml";
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

	}
	
	@FXML
	private void exit(ActionEvent event) {

	}
	
	@FXML
	private void showAbout(ActionEvent event) {

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
			
			final var document = (Document)symbol.value;
			document.declareFunction(PrintFunction.NAME, new PrintFunction(new Writer() {
				@Override
				public void write(char[] cbuf, int off, int len) throws IOException {
					textareaOutput.appendText(new String(cbuf, off, len));
				}
				
				@Override
				public void flush() throws IOException {}
				@Override
				public void close() throws IOException {}
			}));
			final var returnValue = document.execute();
			textareaOutput.appendText("Programm finished with return value: " + returnValue + "\n");
		} catch (Throwable e) {
			textareaOutput.setText(e.getMessage());
			e.printStackTrace();
		}
		
		/* Old code which prints out the tokens
		try {
			while(!lexer.yyatEOF()) {
				final var sym = lexer.next_token();
				
				textareaOutput.appendText("State:      " + lexer.yystate() + "\n");
				textareaOutput.appendText("Match:      " + lexer.yytext() + "\n");
				textareaOutput.appendText("Nummeric:   " + sym + "\n");
				textareaOutput.appendText("Value:      " + sym.value + "\n");
				textareaOutput.appendText("Line:       " + sym.left + "\n");
				textareaOutput.appendText("Column:     " + sym.right + "\n");
				textareaOutput.appendText("\n");
			}
			
			lexer.yyclose();
		} catch (Exception e) {
			textareaOutput.setText(e.getMessage());
			e.printStackTrace();
		}*/
	}
}
