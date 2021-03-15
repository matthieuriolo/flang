package ch.ffhs.fac.flang.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class App extends Application {
	final String mainWindowFXML = "/simple-ide.fxml";
	private Stage mainWindow;

	public static void main(final String[] args) {
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

//		instance.window.getIcons().add(
//			new Image(
//				instance.getClass()
//				.getResource("icon.png")
//				.toString()
//			)
//		);
//		
//		instance.controller = new ActorController(instance);
//		
//		instance.initializeHistoryTable();
//		instance.leftPlayer = new PlayerPaneLeft(instance.controller);
//		instance.rightPlayer = new PlayerPaneRight(instance.controller);
//		
//		instance.borderPane.setLeft(instance.leftPlayer.getVBox());
//		instance.borderPane.setRight(instance.rightPlayer.getVBox());
//		
//		instance.background.fillProperty().bind(instance.settings.getBackground());
//		instance.initializeGridPane();
//		instance.initializeBoardGrid();
//		instance.initializeLabels();
//		instance.initializeTiles();
//		instance.initializeNewGame();
//		
//		//since multiple windows can be opened we make sure that closing the mainwindow will close the whole app
//		instance.window.setOnCloseRequest(e -> instance.menuItemExit(e));
		// }

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
}
