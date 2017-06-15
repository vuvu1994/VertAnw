import java.awt.FlowLayout;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

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
	@FXML
	ComboBox radioComboBox;
	@FXML
	HBox navigationbar;

	@FXML
	Button bibliothek;
	@FXML
	Button youtube;
	@FXML
	Button einstellungen;
	@FXML
	Button openfile;
	@FXML
	Button openlink;
	@FXML
	Button fullscreen;
	@FXML
	Button playlist;

	ObservableList<String> radioList = FXCollections.observableArrayList("1Live","WDR2","WDR5");

	static String comboBoxValue;
	GeneraretSettings settings = new GeneraretSettings();
	Settings radioURI = new Settings();
	Thread t1;
	private int radio = 1;
	public void initialize() throws InterruptedException, SQLException {

		radioComboBox.setItems(radioList);
		fp.prefWidthProperty().bind(ScrollPane.widthProperty());
		fp.prefHeightProperty().bind(ScrollPane.heightProperty());
		fp.setStyle("-fx-background-color: transparent;");
		ScrollPane.setStyle("-fx-background-color: transparent;");

		setGuiElemente();
		bimage.fitWidthProperty().bind(Main.widthProperty());
		bimage.fitHeightProperty().bind(Main.heightProperty());
		bimage.setPreserveRatio(false);
		// Database test
		Database.getDatabase();
		Database.createTable();
		Database.addtoMedia("TestName");
		Database.addtoMedia("TestName2");
		System.out.println(Database.getAllMedia());
		if (new File("Radio").exists()) {
			try {
				FileUtils.deleteDirectory(new File("Radio"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void setGuiElemente() {
		GuiElemente.setScrollPane(ScrollPane);
		GuiElemente.setFlowPane(fp);
		GuiElemente.setMain(Main);
		GuiElemente.setanchorpane(anchorpane);
		GuiElemente.sethbox(hbox);
		GuiElemente.setvbox(vbox);
		GuiElemente.setNavigationbar(navigationbar);

		//Grafiken Navigation START

		Image bibliothekI=new Image(new File("Navigation/Bibliothek.png").toURI().toString());
		ImageView bibliothekIV=new ImageView(bibliothekI);
		bibliothek.setGraphic(bibliothekIV);
		bibliothek.setStyle("-fx-background-color: transparent;");

		Image youtubeI=new Image(new File("Navigation/Youtube.png").toURI().toString());
		ImageView youtubeIV=new ImageView(youtubeI);
		youtube.setGraphic(youtubeIV);
		youtube.setStyle("-fx-background-color: transparent;");

		Image einstellungenI=new Image(new File("Navigation/Einstellungen.png").toURI().toString());
		ImageView einstellungenIV=new ImageView(einstellungenI);
		einstellungen.setGraphic(einstellungenIV);
		einstellungen.setStyle("-fx-background-color: transparent;");

		Image openfileI=new Image(new File("Navigation/openfile.png").toURI().toString());
		ImageView openfileIV=new ImageView(openfileI);
		openfile.setGraphic(openfileIV);
		openfile.setStyle("-fx-background-color: transparent;");

		Image openlinkI=new Image(new File("Navigation/openlink.png").toURI().toString());
		ImageView openlinkIV=new ImageView(openlinkI);
		openlink.setGraphic(openlinkIV);
		openlink.setStyle("-fx-background-color: transparent;");

		Image fullscreenI=new Image(new File("Navigation/fullscreen.png").toURI().toString());
		ImageView fullscreenIV=new ImageView(fullscreenI);
		fullscreen.setGraphic(fullscreenIV);
		fullscreen.setStyle("-fx-background-color: transparent;");

		Image exitI=new Image(new File("Navigation/exit.png").toURI().toString());
		ImageView exitIV=new ImageView(exitI);
		exit.setGraphic(exitIV);
		exit.setStyle("-fx-background-color: transparent;");

		Image playlistI=new Image(new File("Navigation/playlist.png").toURI().toString());
		ImageView playlistIV=new ImageView(playlistI);
		playlist.setGraphic(playlistIV);
		playlist.setStyle("-fx-background-color: transparent;");
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

		GeneraretSettings settings = new GeneraretSettings();
		Settings radioSettings = new Settings("Radio");
		radioSettings.createRadioSettings();
		radioSettings.draw();

		settings.readfiles();
		settings.createEntries();
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
		fileChooser.setTitle("Cover ausw�hlen");
		File file = fileChooser.showOpenDialog(GuiElemente.getMain().getScene().getWindow());
		MediaPlayer.createMediaPlayer(file.getAbsolutePath().toString());

	}

	public void exit() {
		System.out.println("EXIT");
		System.exit(0);
	}

	public void fullscreen(){
		Scene scene =  (Scene) GuiElemente.getMain().getScene();
		 Stage stage = (Stage) scene.getWindow();
		 if (stage.isFullScreen()){
			 stage.setFullScreen(false);
		 }else{
		 stage.setFullScreen(true);
		 }
	}


	public void radioComboBox(ActionEvent event) throws Exception {

		comboBoxValue = radioComboBox.getValue().toString();
		File directory = new File("Radio");

		if (!directory.exists()){
			directory.mkdirs();
		}



				String url = "";
				switch (comboBoxValue) {
					//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					case "1Live":  url = radioURI.getText1live();
						break;
					case "WDR2":  url = radioURI.getTextWdr2();
						break;
					case "WDR5":  url = radioURI.getTextWdr5();
						break;
					default: url= "Invalid";
						break;
				}
				RadioStream rs = new RadioStream(url,radio+"");
				GuiElemente.setRadioStream(rs);
				GuiElemente.getRadiostream().start();
				Thread.sleep(2000);
				MediaPlayer.createMediaPlayer("Radio/"+radio+".mp3");
									//FX Stuff done here
				++radio;
				//rs.kill();
	}

	public void playlist(){
		fp.getChildren().clear();
		GeneratePlaylist pl = new GeneratePlaylist();
		pl.readfiles();
		pl.create();
	}


}
