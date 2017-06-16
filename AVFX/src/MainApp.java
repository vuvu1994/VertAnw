import java.io.IOException;

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
		GenerateSettings.createMediaSetting();
		Parent root = FXMLLoader.load(getClass().getResource("Gui.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Media Player");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setResizable(true);
		primaryStage.show();
		primaryStage.setFullScreenExitHint("");
		primaryStage.setFullScreen(false);
		primaryStage.setMinWidth(1138);
		primaryStage.setMinHeight(640);
		primaryStage.setWidth(1280);
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
