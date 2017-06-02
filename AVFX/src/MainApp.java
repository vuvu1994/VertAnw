import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileSystemView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException, InterruptedException {

		Settings.createProperties();
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
	}

}
