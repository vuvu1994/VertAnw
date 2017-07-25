import java.io.*;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

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
	Button playlist;
	@FXML
	ProgressBar progress;
	@FXML
	Text status;
	ObservableList<String> radioList = FXCollections.observableArrayList("1Live","WDR2","WDR5");
	static String comboBoxValue;
	GenerateSettings settings = new GenerateSettings();
	Settings radioURI = new Settings();
	Thread t1;
	private int radio = 1;
	public void initialize() throws InterruptedException, SQLException {

		radioComboBox.setItems(radioList);
		fp.prefWidthProperty().bind(ScrollPane.widthProperty());
		fp.prefHeightProperty().bind(ScrollPane.heightProperty());
		fp.setStyle("-fx-background-color: transparent;");
		ScrollPane.setStyle("-fx-background-color: transparent;");

		try {
			setGuiElemente();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		bimage.fitWidthProperty().bind(Main.widthProperty());
		bimage.fitHeightProperty().bind(Main.heightProperty());
		bimage.setPreserveRatio(false);

		Database.getDatabase();
		Database.createTable();
		if (new File("Radio").exists()) {
			try {
				FileUtils.deleteDirectory(new File("Radio"));
			} catch (IOException e) {
				System.out.println ("Konnte nicht gelöscht werden");
			}
		}

	}

	private void setGuiElemente() throws URISyntaxException {
		GuiElemente.setScrollPane(ScrollPane);
		GuiElemente.setFlowPane(fp);
		GuiElemente.setMain(Main);
		GuiElemente.setanchorpane(anchorpane);
		GuiElemente.sethbox(hbox);
		GuiElemente.setvbox(vbox);
		GuiElemente.setNavigationbar(navigationbar);
		GuiElemente.setProgressBar(progress);
		GuiElemente.setRadioAktiv(false);
		GuiElemente.setStatus(status);
        //Buttons für Navi
		ClassLoader cl = getClass().getClassLoader();
		Image bibliothekI=new Image(cl.getResource("resources/Bibliothek.png").toExternalForm());
		ImageView bibliothekIV=new ImageView(bibliothekI);
		bibliothekIV.setPreserveRatio(false);

		bibliothek.setGraphic(bibliothekIV);
		bibliothek.setStyle("-fx-background-color: transparent;");

		Image youtubeI=new Image(cl.getResource("resources/Youtube.png").toExternalForm());
		ImageView youtubeIV=new ImageView(youtubeI);
		youtube.setGraphic(youtubeIV);
		youtube.setStyle("-fx-background-color: transparent;");

		Image einstellungenI=new Image(cl.getResource("resources/Einstellungen.png").toExternalForm());
		ImageView einstellungenIV=new ImageView(einstellungenI);
		einstellungen.setGraphic(einstellungenIV);
		einstellungen.setStyle("-fx-background-color: transparent;");

		Image openfileI=new Image(cl.getResource("resources/openfile.png").toExternalForm());
		ImageView openfileIV=new ImageView(openfileI);
		openfile.setGraphic(openfileIV);
		openfile.setStyle("-fx-background-color: transparent;");

		Image openlinkI=new Image(cl.getResource("resources/openlink.png").toExternalForm());
		ImageView openlinkIV=new ImageView(openlinkI);
		openlink.setGraphic(openlinkIV);
		openlink.setStyle("-fx-background-color: transparent;");

		Image playlistI=new Image(cl.getResource("resources/playlist.png").toExternalForm());
		ImageView playlistIV=new ImageView(playlistI);
		playlist.setGraphic(playlistIV);
		playlist.setStyle("-fx-background-color: transparent;");
		GuiElemente.getNavigationbar().setSpacing(5);
	}

	public void bibliothek() throws Exception {
		fp.getChildren().clear();
		new GenerateObjekts().start();

	}

	public void youtube() throws Exception {
		fp.getChildren().clear();
		InternetBrowser.createWebView();

	}



	public void einstellungen() throws Exception {
		fp.getChildren().clear();

		GenerateSettings settings = new GenerateSettings();
		Settings radioSettings = new Settings("Allgemeine Einstellungen");
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

		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			System.out.println("URL: " + result.get());
			MediaPlayer.createMediaPlayerwithURL(result.get().toString());
		}


	}

	public void openfile() throws Exception {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Media-Auswahl");
			FileChooser.ExtensionFilter extFilter =
					new FileChooser.ExtensionFilter("Media-Datein", "*.mp4", "*.mp3", "*.wav", "*.flv");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(GuiElemente.getMain().getScene().getWindow());
			MediaPlayer.createMediaPlayer(file.getAbsolutePath().toString());

		}catch(Exception e) {
			Status.make("Keine Datei gefunden");
		}

	}







	public void radioComboBox(ActionEvent event)  {
		Status.make("Buffering");

		if (!GuiElemente.getRadioAktiv()) {
			GuiElemente.setRadioAktiv(true);
			radioComboBox.showingProperty().addListener((obs, wasShowing, isShowing) -> {
				if (!isShowing) {


					comboBoxValue = radioComboBox.getValue().toString();
					File directory = new File("Radio");

					if (!directory.exists()) {
						directory.mkdirs();
					}


					String url = "";
					switch (comboBoxValue) {

						case "1Live":
							url = radioURI.getText1live();
							break;
						case "WDR2":
							url = radioURI.getTextWdr2();
							break;
						case "WDR5":
							url = radioURI.getTextWdr5();
							break;
						default:
							url = "Invalid";
							break;
					}

					RadioStream rs = new RadioStream(url, radio + "");
					GuiElemente.setRadioStream(rs);
					GuiElemente.getRadiostream().start();
					File size = new File("Radio/" + radio + ".mp3");
					while (size.length() < 200000) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					MediaPlayer.createMediaPlayerRadio("Radio/" + radio + ".mp3");

					++radio;
				}


			});

		}
	}

	public void playlist(){
		fp.getChildren().clear();
		GeneratePlaylist pl = new GeneratePlaylist();
		pl.readfiles();
		pl.create();
	}


}
