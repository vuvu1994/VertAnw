import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;

public class Settings {
	

	public static Properties prop = new Properties();
	public static OutputStream output;
	public static InputStream input;

	// Saving Properties and the changes in a XML File
	public static void save() {

		try {
			output = new FileOutputStream("settings.xml");
			prop.storeToXML(output, null);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Loads Properties from a XML File
	public static void load() {
		try {
			input = new FileInputStream("settings.xml");

			// load a properties file
			prop.loadFromXML(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// Creates a Property with default values if there is no XML File
	public static void createProperties() {
		File file = new File("settings.xml");
		if (file.exists()) {

		} else {
			prop.setProperty("1. Audiothek", "C:");
			prop.setProperty("2. Audiothek", "D:");
			prop.setProperty("3. Audiothek", "G");
			prop.setProperty("1. Videothek", "600");
			prop.setProperty("2. Videothek", "400");
			prop.setProperty("3. Videothek", "DBZ");
			prop.setProperty("FontSize", "12");
			prop.setProperty("FontColor", "blau");
			prop.setProperty("PicSize", "klein");
			save();
		}
	}

	public static void setFistAudiothek(String fa) {
		
		prop.setProperty("1. Audiothek", fa);
		save();

	}

	public static String getFistAudiothek() {
		load();
		String s = prop.getProperty("1. Audiothek");
		return s;
	}

	public static void setSecondAudiothek(String fa) {

		prop.setProperty("2. Audiothek", fa);
		save();

	}

	public static String getSecondAudiothek() {
		load();
		String s = prop.getProperty("2. Audiothek");
		return s;
	}

	public static void setThirdAudiothek(String fa) {

		prop.setProperty("3. Audiothek", fa);
		save();

	}

	public static String getThirdAudiothek() {
		load();
		String s = prop.getProperty("3. Audiothek");
		return s;
	}
	
	public static void setFirstVideothek(String fa) {

		prop.setProperty("1. Videothek", fa);
		save();

	}

	public static String getFirstVideothek() {
		load();
		String s = prop.getProperty("1. Videothek");
		return s;
	}
	
	public static void setSecondVideothek(String fa) {

		prop.setProperty("2. Videothek", fa);
		save();

	}

	public static String getSecondVideothek() {
		load();
		String s = prop.getProperty("2. Videothek");
		return s;
	}
	
	public static void setThirdVideothek(String fa) {

		prop.setProperty("3. Videothek", fa);
		save();

	}

	public static String getThirdVideothek() {
		load();
		String s = prop.getProperty("3. Videothek");
		return s;
	}

	public static void setFontSize(String fa) {

		prop.setProperty("FontSize", fa);
		save();

	}

	public static String getFontSize() {
		load();
		String s = prop.getProperty("FontSize");
		return s;
	}
	
	public static void setFontColor(String fa) {

		prop.setProperty("FontColor", fa);
		save();

	}

	public static String getFontColor() {
		load();
		String s = prop.getProperty("FontColor");
		return s;
	}
	
	public static void setPicSize(String fa) {

		prop.setProperty("PicSize", fa);
		save();

	}

	public static String getPicSize() {
		load();
		String s = prop.getProperty("PicSize");
		return s;
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////
	static Label labelAudio = new Label("Wähle ersten Audio Ordner");
	static Label labelAudio2 = new Label("Wähle zweiten Audio Ordner");
	static Label labelAudio3 = new Label("Wähle dritter Audio Ordner");
	static Label labelVideo = new Label("Wähle ersten Video Ordner");
	static Label labelVideo2 = new Label("Wähle zweiten Video Ordner");
	static Label labelVideo3 = new Label("Wähle dritten Video Ordner");
	static Label labelFontSize = new Label("Wähle eine Schrifft größe");
	static Label labelFontColor = new Label("Wähle Schriftfarbe");
	static Label labelPicSize = new Label("Wähle Bild größe");

	//////////////////////////////////////////////////////////////////////////////////////////////////
	static Button buttonAudio = new Button(getFistAudiothek());
	static Button buttonAudio2 = new Button(getSecondAudiothek());
	static Button buttonAudio3 = new Button(getThirdAudiothek());
	static Button buttonVideo = new Button(getFirstVideothek());
	static Button buttonVideo2 = new Button(getSecondVideothek());
	static Button buttonVideo3 = new Button(getThirdVideothek());

	//////////////////////////////////////////////////////////////////////////////////////////////////
	static ComboBox<String> dropFontSize = new ComboBox();
	static ComboBox<String> dropFontColor = new ComboBox();
	static ComboBox<String> dropPicSize = new ComboBox();
	

	
	public static void createElements() {
		
		dropFontSize.getItems().addAll("12", "14", "16", "18", "20", "22", "24", "26");
		dropFontSize.setValue(getFontSize());

		dropFontColor.getItems().addAll("blau", "rot", "grün");
		dropFontColor.setValue(getFontColor());

		dropPicSize.getItems().addAll("klein", "mittel", "groß");
		dropPicSize.setValue(getPicSize());
		
		FlowPane fp = GuiElemente.getFlowPane();
		GridPane gp = new GridPane();

		gp.setVgap(85);
		gp.setHgap(10);

		//////////////////////////////////////////////////////////////////////////////////////////////////
		gp.getChildren().add(labelAudio);
		gp.setConstraints(labelAudio, 0, 0);

		gp.getChildren().add(labelAudio2);
		gp.setConstraints(labelAudio2, 0, 1);

		gp.getChildren().add(labelAudio3);
		gp.setConstraints(labelAudio3, 0, 2);

		gp.getChildren().add(labelVideo);
		gp.setConstraints(labelVideo, 0, 3);

		gp.getChildren().add(labelVideo2);
		gp.setConstraints(labelVideo2, 0, 4);

		gp.getChildren().add(labelVideo3);
		gp.setConstraints(labelVideo3, 0, 5);

		gp.getChildren().add(labelFontSize);
		gp.setConstraints(labelFontSize, 0, 6);

		gp.getChildren().add(labelFontColor);
		gp.setConstraints(labelFontColor, 0, 7);

		gp.getChildren().add(labelPicSize);
		gp.setConstraints(labelPicSize, 0, 8);

		gp.getChildren().add(buttonAudio);
		gp.setConstraints(buttonAudio, 1, 0);

		gp.getChildren().add(buttonAudio2);
		gp.setConstraints(buttonAudio2, 1, 1);

		gp.getChildren().add(buttonAudio3);
		gp.setConstraints(buttonAudio3, 1, 2);

		gp.getChildren().add(buttonVideo);
		gp.setConstraints(buttonVideo, 1, 3);

		gp.getChildren().add(buttonVideo2);
		gp.setConstraints(buttonVideo2, 1, 4);

		gp.getChildren().add(buttonVideo3);
		gp.setConstraints(buttonVideo3, 1, 5);

		gp.getChildren().add(dropFontSize);
		gp.setConstraints(dropFontSize, 1, 6);

		gp.getChildren().add(dropFontColor);
		gp.setConstraints(dropFontColor, 1, 7);

		gp.getChildren().add(dropPicSize);
		gp.setConstraints(dropPicSize, 1, 8);

		fp.getChildren().add(gp);

		chooser(buttonAudio);
		chooser(buttonAudio2);
		chooser(buttonAudio3);
		chooser(buttonVideo);
		chooser(buttonVideo2);
		chooser(buttonVideo3);
	
	}

	public static void chooser(Button btn){
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				DirectoryChooser chooser = new DirectoryChooser();
				File file = chooser.showDialog(GuiElemente.getMain().getScene().getWindow());
				btn.setText(file.toString());
				setFistAudiothek(btn.getText());
				setSecondAudiothek(btn.getText());
				setThirdAudiothek(btn.getText());
				setFirstVideothek(btn.getText());
				setSecondVideothek(btn.getText());
				setThirdVideothek(btn.getText());
			}
		});
	}

}
