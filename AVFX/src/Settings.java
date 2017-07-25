import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;


import java.io.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

/**
 * Created by Vural on 07.06.2017.
 */
public class Settings {
	private String Name;
	ListView lv;
	private VBox vb;
	public static Properties prop = new Properties();
	public static OutputStream output;
	public static InputStream input;

	public Settings(String Name){
		this.Name = Name;
		InternetBrowser.removeWebView();
		GuiElemente.getNavigationbar().getChildren().clear();
	}

	public Settings() {

	}


	public void save() {

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
	public void load() {
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
	public void createRadioProperties() {

		File file = new File("settings.xml");
		if (file.exists()) {
			return;
		} else {
			prop.setProperty("1Live","http://wdr-1live-live.icecast.wdr.de/wdr/1live/live/mp3/128/stream.mp3");
			prop.setProperty("WDR2","http://wdr-wdr2-rheinruhr.icecast.wdr.de/wdr/wdr2/rheinruhr/mp3/128/stream.mp3");
			prop.setProperty("WDR5","http://wdr-wdr5-live.icecast.wdr.de/wdr/wdr5/live/mp3/128/stream.mp3");
			prop.setProperty("Scraper", "false");
			prop.setProperty("Videoformat", "true");
			save();
		}
	}

	public void setText1live(String fa) {

		prop.setProperty("1Live", fa);
		save();

	}
	public String getText1live() {
		load();
		String s = prop.getProperty("1Live");
		return s;
	}

	public void setTextWdr2(String fa) {

		prop.setProperty("WDR2", fa);
		save();

	}

	public String getTextWdr2 () {
		load();
		String s = prop.getProperty("WDR2");
		return s;
	}
	public void setTextWdr5(String fa) {

		prop.setProperty("WDR5", fa);
		save();

	}

	public String getTextWdr5 () {
		load();
		String s = prop.getProperty("WDR5");
		return s;
	}
	public void setScrapper(String fa) {

		prop.setProperty("Scraper", fa);
		save();

	}

	public boolean getScrapper () {
		load();
		boolean s = Boolean.valueOf(prop.getProperty("Scraper"));
		return s;
	}
	public void setVideoformat(String fa) {

		prop.setProperty("Videoformat", fa);
		save();

	}

	public boolean getVideoformat () {
		load();
		boolean s = Boolean.valueOf(prop.getProperty("Videoformat"));
		return s;
	}

	public ArrayList<String> readIntoArrayList (){
		Scanner s = null;
		try {
			s = new Scanner(new File("Settings\\"+Name+".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<String>();
		//while (s.hasNext()){    list.add(s.next()); } //Für das einlesen von einzelenen Buchstaben nicht Zeilen
		while (s.hasNextLine()){
			list.add(s.nextLine());
		}
		s.close();
		return list;
	}
	public ArrayList<String> readDirectories (String dateiName){
		Scanner s = null;
		try {
			s = new Scanner(new File("Settings\\"+dateiName+".txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> list = new ArrayList<String>();
		//while (s.hasNext()){    list.add(s.next()); } //Für das einlesen von einzelenen Buchstaben nicht Zeilen
		while (s.hasNextLine()){
			list.add(s.nextLine());
		}
		s.close();
		return list;
	}

	public void writeToFile(ArrayList<String> list){
		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter("Settings\\"+Name+".txt"));
			for(String x:list){
				writer.write(x);
				writer.newLine();
			}
			writer.close();
		}catch (IOException e){

		}
	}
	public void deleteFromFile(ArrayList<String> list, String search){

		for(int zahl = 0; zahl<list.size();zahl++){
			if(list.get(zahl).equals(search)){
				list.remove(zahl);
				writeToFile(list);
			}
		}
	}

	public void createHelper(){
		vb = new VBox();
		vb.setPrefHeight(400);
		vb.setSpacing(15);
		vb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
		Text name = new Text();
		name.setText(Name);
		name.setWrappingWidth(300);
		name.setFont(Font.font("Verdana", 20));
		name.setFill(Color.WHITE);
		vb.getChildren().add(name);
	}

	public void createRadioSettings(){
		createHelper();
		Label label1live = new Label("1Live Link");
		Label labelWdr2 = new Label("WDR2 Link");
		Label labelWdr5 = new Label("WDR5 Link");
		TextField text1live = new TextField(getText1live());
		TextField textWdr2 = new TextField(getTextWdr2());
		TextField textWdr5 = new TextField(getTextWdr5());
		CheckBox checkBoxScrapper = new CheckBox("Scraper aktivieren/deaktivieren");
		checkBoxScrapper.setSelected(getScrapper());
		CheckBox checkboxVideoformat = new CheckBox("Videoformat beibehalten");
		checkboxVideoformat.setSelected(getVideoformat());


		text1live.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
				setText1live(text1live.getText());

			}
		});
		textWdr2.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
				setTextWdr2(textWdr2.getText());

			}
		});
		textWdr5.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
								String oldValue, String newValue) {
				setTextWdr5(textWdr5.getText());

			}
		});

		checkBoxScrapper.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov,
                                Boolean old_val, Boolean new_val) {
                String s = String.valueOf(checkBoxScrapper.isSelected());
                setScrapper(s);
            }
        });

		checkboxVideoformat.selectedProperty().addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov,
								Boolean old_val, Boolean new_val) {
				String s = String.valueOf(checkboxVideoformat.isSelected());
				setVideoformat(s);
			}
		});

        vb.getChildren().addAll(label1live,text1live,labelWdr2,textWdr2,labelWdr5,textWdr5,checkBoxScrapper,checkboxVideoformat);
	}



	public void create(){
		createHelper();
		lv = new ListView();
		vb.getChildren().add(lv);
		HBox hb = new HBox();
		Button b1 = new Button("Hinzufügen");
		Button b2 = new Button ("Entfernen");
		hb.setSpacing(10);
		hb.setPadding(new Insets(5,5,5,5));
		hb.getChildren().add(b1);
		hb.getChildren().add(b2);
		hb.setAlignment(Pos.CENTER);
		vb.getChildren().add(hb);


		b1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				try {
					DirectoryChooser chooser = new DirectoryChooser();
					File file = chooser.showDialog(GuiElemente.getMain().getScene().getWindow());

					String  values= lv.getItems().toString();
					String [] helper = values.split(",");

					boolean ermittler = true;

					for(int i = 0; helper.length>i; i++) {
						String helper2 = helper[i].toString();
						helper2=helper2.replace("[","");
						helper2=helper2.replace("]","");
						helper2= helper2.trim();
						if(helper2.equalsIgnoreCase(file.toString())){
							ermittler=false;
						}else{

						}
					}
					if(ermittler==false){

					}else {
						try {
							lv.getItems().add(file.toString());
							if(Name.equals("Audiothek")){
								FileWriter fw = new FileWriter("Settings\\Audiothek.txt", true);
								BufferedWriter ausgabe = new BufferedWriter(fw);
								ausgabe.write(file.toString());
								ausgabe.newLine();
								ausgabe.close();
							}else if(Name.equals("Mediathek")){
								FileWriter fw = new FileWriter("Settings\\Mediathek.txt", true);
								BufferedWriter ausgabe = new BufferedWriter(fw);
								ausgabe.write(file.toString());
								ausgabe.newLine();
								ausgabe.close();
							}
						} catch (IOException es) {
							es.printStackTrace();
						}
					}
				}catch (NullPointerException e1){

				}
			}
		});

		b2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final int selectedIdx = lv.getSelectionModel().getSelectedIndex();
				if (selectedIdx != -1) {
					String itemToRemove = lv.getSelectionModel().getSelectedItem().toString();

					final int newSelectedIdx =
							(selectedIdx == lv.getItems().size() - 1)
									? selectedIdx - 1
									: selectedIdx;

					lv.getItems().remove(selectedIdx);
					lv.getSelectionModel().select(newSelectedIdx);
					if(Name.equals("Audiothek")){
						deleteFromFile(readIntoArrayList(),itemToRemove);
					}else if(Name.equals("Mediathek")){
						deleteFromFile(readIntoArrayList(),itemToRemove);
					}
				};
			}
		});
	}

	public void draw(){GuiElemente.getFlowPane().getChildren().add(vb); }

	public void add(String mo){
		lv.getItems().add(mo);
		lv.refresh();

	}
}

