import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class MediaObjekt {
	String Name;
	String Path;
	String Bild;
	Image image;
	AnchorPane AM;
	int sizew = 300;
	int sizeh = 450;
	VBox vb;
	ImageView iv;
	ClassLoader cl = getClass().getClassLoader();
	public MediaObjekt(){
		
	}
	
	public void setName(String Name) {
		this.Name = Name;
	}

	public void setPath(String Path) {
		this.Path = Path;
	}

	public void setBild(String Bild) {
		this.Bild = Bild;
	}
	
	
	public void createMediaObjekt() throws IOException {
		Button bu = new Button("");
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				bu.setOpacity(0.0);
			}
		});
		vb = new VBox();
		HBox hb = new HBox();
		AM = new AnchorPane();
		if (!Name.contains(".mp3")&& !Name.contains(".wav")) {
			sizeh = 450;
			sizew = 300;
		}else{
			sizeh = 300;
			sizew = 300;
		}
		AM.getChildren().add(hb);
		AM.getChildren().add(bu);
		File file = new File(Bild);
		AVFXSettings settings = new AVFXSettings();
		if (settings.getScrapper()&& !file.getName().contains(".mp3")&& !file.getName().contains(".wav")) {
			if (!file.exists()) {
				WebScraper.getData(Name);
			}
		}
		if (!file.exists() ){
			System.out.println("Cover nicht vorhanden");
			if (!file.getName().contains(".mp3")&& !file.getName().contains(".wav")) {
				image = new Image(cl.getResource("resources/platzhaltervideo.png").toExternalForm());
			}else{
				image = new Image(cl.getResource("resources/platzhalteraudio.png").toExternalForm());
			}


		}else{
			image = new Image(file.toURI().toString());
		}

		
		iv = new ImageView(image);
		iv.setFitWidth(sizew);
		iv.setFitHeight(sizeh);
		iv.setPreserveRatio(false);
		iv.setSmooth(true);
		iv.setCache(true);
		iv.setCacheHint(CacheHint.QUALITY);

		vb.getChildren().add(iv);

		Text tname = new Text(Name);
		tname.setFont(Font.font("Verdana", 16));
		tname.setWrappingWidth(sizew);
		tname.setFill(Color.WHITE);
		hb.getChildren().add(vb);
		vb.getChildren().add(tname);
		vb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");

		bu.setPrefWidth(tname.getWrappingWidth());
		bu.setPrefHeight(iv.getFitHeight());
	
		bu.toFront();
		vb.toBack();
		
		final ContextMenu contextMenu = new ContextMenu();
		MenuItem cover = new MenuItem("Cover setzen");
		MenuItem scraper = new MenuItem("Cover automatisch suchen");
		
		contextMenu.getItems().addAll(cover, scraper);
		
		bu.setContextMenu(contextMenu);
		bu.setOnMousePressed(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        if (event.isSecondaryButtonDown()) {
		            contextMenu.show(bu, event.getScreenX(), event.getScreenY());
		        }
		    }
		});
		bu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					if (!GuiElemente.playlist) {
						System.out.println("Eventtyp bei MediaObjekt: " + e.getEventType());
						AVFXMediaPlayer.createMediaPlayer(Path + Name);
					}else{

						Playlist pl = GuiElemente.getPlaylist();

						pl.add(Path+ Name);
						Status.make(Name + " hinzugefügt");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cover.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        setCover();
		    }
		});
		scraper.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.out.println("tut was");
				File dest = new File("Cover/"+Name+".jpg");
				try {
					FileUtils.forceDelete(dest);
				} catch (IOException e) {
					System.out.println("keine Datei gefunden");
				}

				WebScraper.getData(Name);


				if (dest.exists()) {
					iv.setImage(new Image(dest.toURI().toString()));
				}else{

					if (!Name.contains(".mp3")&& !Name.contains(".wav")) {
						iv.setImage(new Image(cl.getResource("resources/platzhaltervideo.png").toExternalForm()));
					}else{
						iv.setImage(new Image(cl.getResource("resources/platzhalteraudio.png").toExternalForm()));
					}

				}

			}
		});

	}
	
	public AnchorPane getObjekt() {
		return AM;
	}

	public MediaObjekt getMediaObjekt(){
		return this;
	}
	public void setCover(){
		try {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Cover auswählen");
		File file = fileChooser.showOpenDialog(GuiElemente.getMain().getScene().getWindow());
		iv.setImage(new Image(file.toURI().toString()));
		
		File dest = new File("Cover/"+Name+".jpg");
		try {
		    FileUtils.copyFile(file, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		}catch (Exception e) {
			Status.make("Kein Cover ausgew�hlt");
		}

	}
}
