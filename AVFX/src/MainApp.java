import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException, InterruptedException {
		Settings einstellungen = new Settings();
		einstellungen.createRadioProperties();
		GeneraretSettings.createMediaSetting();
		Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Media Player");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(true);
		primaryStage.show();
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreen(false);
		primaryStage.setWidth(1140);
		primaryStage.setHeight(720);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent we) {
				System.out.println("Stage is closing");
				System.out.println("EXIT");
				System.exit(0);
			}
		});
	}

}
