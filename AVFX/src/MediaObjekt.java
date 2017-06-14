import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

import javax.print.attribute.standard.Media;

public class MediaObjekt {
	String Name;
	String Path;
	String Bild;
	Image image;
	AnchorPane AM;
	int sizew = 0;
	int sizeh = 0;
	VBox vb;
	ImageView iv;
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
		
		AM.getChildren().add(hb);
		AM.getChildren().add(bu);
		File file = new File(Bild);
		if (!file.exists()){
			WebScraper.getData(Name);
		}
		if (!file.exists()){
			System.out.println("Cover nicht vorhanden");
			file = new File("Cover/platzhaltervideo.png");
		}
		image = new Image(file.toURI().toString());
		
		iv = new ImageView(image);
		iv.setFitWidth(300);
		iv.setFitHeight(450);
		iv.setPreserveRatio(false);
		iv.setSmooth(true);
		iv.setCache(true);
		iv.setCacheHint(CacheHint.QUALITY);

		vb.getChildren().add(iv);

		Text tname = new Text(Name);
		tname.setFont(Font.font("Verdana", 16));
		tname.setWrappingWidth(300);
		tname.setFill(Color.WHITE);
		hb.getChildren().add(vb);
		vb.getChildren().add(tname);
		vb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");

		bu.setPrefWidth(tname.getWrappingWidth());
		bu.setPrefHeight(iv.getFitHeight());
	
		bu.toFront();
		vb.toBack();
		
		final ContextMenu contextMenu = new ContextMenu();
		MenuItem cover = new MenuItem("Cover");
		MenuItem playlist = new MenuItem("Playlist");
		
		contextMenu.getItems().addAll(cover, playlist);
		
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
						MediaPlayer.createMediaPlayer(Path + Name);
					}else{

						Playlist pl = GuiElemente.getPlaylist();

						pl.add(Path+ Name);
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

	}
	
	public AnchorPane getObjekt() {
		return AM;
	}

	public MediaObjekt getMediaObjekt(){
		return this;
	}
	public void setCover(){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Cover ausw�hlen");
		File file = fileChooser.showOpenDialog(GuiElemente.getMain().getScene().getWindow());
		iv.setImage(new Image(file.toURI().toString()));
		
		File dest = new File("Cover/"+Name+".jpg");
		try {
		    FileUtils.copyFile(file, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		}

	}
}
