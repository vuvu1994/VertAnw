import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MediaObjekt {
	String Name;
	String Path;
	String Bild;
	Image image;
	AnchorPane AM;
	int sizew = 0;
	int sizeh = 0;
	VBox vb;

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
		image = new Image(file.toURI().toString());
		
		ImageView iv = new ImageView(image);
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
		vb.setOpacity(1);
		vb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");

		bu.setPrefWidth(tname.getWrappingWidth());
		bu.setPrefHeight(iv.getFitHeight());
	
		bu.toFront();
		vb.toBack();
	
		
		bu.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				try {
					System.out.println("Eventtyp bei MediaObjekt: " + e.getEventType());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}
	
	public AnchorPane getObjekt() {
		return AM;
	}
}
