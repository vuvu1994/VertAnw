import java.io.File;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class MediaPlayer {

	 static MediaView mediaView;
	 static Media media;
	 static javafx.scene.media.MediaPlayer mediaPlayer;
	 
	 
	 public static void createMediaPlayer(String datei){
		 
		 media = new Media(new File(datei).toURI().toString());
		 mediaPlayer = new javafx.scene.media.MediaPlayer(media);
		 mediaView = new MediaView(mediaPlayer);
		 
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					
					GuiElemente.gethbox().setVisible(false);
					GuiElemente.getanchorpane().setVisible(false);
					GuiElemente.getvbox().setVisible(false);
				}
			});
	
		
		 
		 HBox hb = new HBox();
		 Button Play = new Button("Pause");
		 Button back = new Button("Zurückspulen");
		 Button forward = new Button("Vorspulen");
		 Slider dauer = new Slider();
		 Slider laut = new Slider();
		 Button fullscreen = new Button("Vollbild");
		 Button ende = new Button("Beenden");
		 hb.getChildren().add(Play);
		 hb.getChildren().add(back);
		 hb.getChildren().add(forward);
		 hb.getChildren().add(dauer);
		 hb.getChildren().add(laut);
		 hb.getChildren().add(fullscreen);
		 hb.getChildren().add(ende);
		 AnchorPane m = GuiElemente.getMain();
		 m.getChildren().add(mediaView);
		 m.getChildren().add(hb);
		 m.setBottomAnchor(hb, 0.0);
		 m.setRightAnchor(hb, 0.0);
		 m.setLeftAnchor(hb, 0.0);
			mediaView.fitWidthProperty().bind(m.widthProperty());
			mediaView.fitHeightProperty().bind(m.heightProperty());
			mediaView.setPreserveRatio(false);
		 mediaPlayer.setAutoPlay(true);
		 

			Play.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						System.out.println("Eventtyp bei Play: " + e.getEventType());
						if (mediaPlayer.getStatus().toString()=="PLAYING"){
						System.out.println(mediaPlayer.getStatus());
						mediaPlayer.pause();
						Play.setText("Play");
						}else{
						mediaPlayer.play();
						Play.setText("Pause");
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			fullscreen.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						System.out.println("Eventtyp bei fullscreen: " + e.getEventType());
						 Scene scene =  (Scene) GuiElemente.getMain().getScene();
						 Stage stage = (Stage) scene.getWindow();
						 stage.setFullScreen(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			ende.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						System.out.println("Eventtyp bei ende: " + e.getEventType());
						 mediaPlayer.pause();
						 GuiElemente.getMain().getChildren().remove(mediaView);
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									
									GuiElemente.gethbox().setVisible(true);
									GuiElemente.getanchorpane().setVisible(true);
									GuiElemente.getvbox().setVisible(true);
								}
							});
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		 }
	 
	 
	 
}
