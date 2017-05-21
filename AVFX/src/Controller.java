import java.awt.FlowLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class Controller {
	@FXML
	ImageView bimage;
	@FXML
	AnchorPane Main;
	@FXML
	AnchorPane anchorpane;
	@FXML
	ScrollPane ScrollPane;
	@FXML
	FlowPane fp;
	@FXML
	VBox vbox;
	@FXML
	HBox hbox;
	@FXML
	Button exit;

	public void initialize() throws InterruptedException, SQLException {
		Settings.createProperties();
		fp.prefWidthProperty().bind(ScrollPane.widthProperty());
		fp.prefHeightProperty().bind(ScrollPane.heightProperty());
		fp.setStyle("-fx-background-color: transparent;");
		ScrollPane.setStyle("-fx-background-color: transparent;");

		GuiElemente.setScrollPane(ScrollPane);
		GuiElemente.setFlowPane(fp);
		GuiElemente.setMain(Main);
		GuiElemente.setanchorpane(anchorpane);
		GuiElemente.sethbox(hbox);
		GuiElemente.setvbox(vbox);
		bimage.fitWidthProperty().bind(Main.widthProperty());
		bimage.fitHeightProperty().bind(Main.heightProperty());
		bimage.setPreserveRatio(false);
		// Database test
		Database.getDatabase();
		Database.createTable();
		Database.addtoMedia("TestName");
		Database.addtoMedia("TestName2");
		System.out.println(Database.getAllMedia());
		
		// Database test Ende
	}

	public void bibliothek() throws Exception {
		fp.getChildren().clear();
		new GenerateObjekts().start();

	}

	public void youtube() throws Exception {
		fp.getChildren().clear();
		InternetBrowser.createWebView();

	}

	public void youtubeBackwards() throws Exception {

		Platform.runLater(() -> {
			InternetBrowser.engine.executeScript("history.back()");
		});

	}

	public void youtubeForwards() {
		Platform.runLater(() -> {
			InternetBrowser.engine.executeScript("history.forward()");
		});
	}

	public void einstellungen() throws Exception {
		fp.getChildren().clear();
		Settings.createElements();
	}

	public void urlopen() throws Exception {

		TextInputDialog dialog = new TextInputDialog("URL");
		dialog.setTitle("Url eingeben");
		dialog.setHeaderText("AVFX HTTP Player");
		dialog.setContentText("Link zur Datei:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("URL: " + result.get());
		}
		MediaPlayer.createMediaPlayerwithURL(result.get().toString());

	}

	public void openfile() throws Exception {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Cover auswählen");
		File file = fileChooser.showOpenDialog(GuiElemente.getMain().getScene().getWindow());
		MediaPlayer.createMediaPlayer(file.getAbsolutePath().toString());

	}

	public void exit() {
		System.out.println("EXIT");
		System.exit(0);
	}

}
