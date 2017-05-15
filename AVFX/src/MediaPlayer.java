import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
		 createElements();
		 }
	 

	 public static void createMediaPlayerwithURL(String datei){
		 media = new Media(datei);
		 createElements();
		 }
	 
	 public static void createElements(){
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
	
		
		 AnchorPane ap = new AnchorPane();
		 
		 Image bg=new Image(new File("Controls/bg.jpg").toURI().toString());
		 ImageView bgIV=new ImageView(bg);	
		 bgIV.setPreserveRatio(false);
		 ap.getChildren().add(bgIV);
		 HBox hb = new HBox();
		 HBox hb2 = new HBox();
		 Image playI=new Image(new File("Controls/pause.png").toURI().toString());
		 ImageView playIV=new ImageView(playI);
		 Button Play = new Button("",playIV);
		 Image backI=new Image(new File("Controls/back.png").toURI().toString());
		 ImageView backIV=new ImageView(backI);
		 Button back = new Button("",backIV);
		 Image forwardI=new Image(new File("Controls/forward.png").toURI().toString());
		 ImageView forwardIV=new ImageView(forwardI);
		 Button forward = new Button("",forwardIV);
		 Slider dauer = new Slider();
		 dauer.setPrefWidth(500);
		 Slider laut = new Slider();
		 Image fullI=new Image(new File("Controls/fullscreen.png").toURI().toString());
		 ImageView fullIV=new ImageView(fullI);
		 Button fullscreen = new Button("",fullIV);
		 Image endeI=new Image(new File("Controls/exit.png").toURI().toString());
		 ImageView endeIV=new ImageView(endeI);
		 Button ende = new Button("",endeIV);
		 hb.getChildren().add(Play);
		 hb.getChildren().add(back);
		 hb.getChildren().add(forward);
		 hb.getChildren().add(dauer);
		 hb2.getChildren().add(fullscreen);
		 hb2.getChildren().add(ende);
		 hb2.getChildren().add(laut);
		 
		 hb.setAlignment(Pos.CENTER_LEFT);
		 hb2.setAlignment(Pos.CENTER_RIGHT);
		 ap.getChildren().add(hb);
		 ap.getChildren().add(hb2);
		 AnchorPane m = GuiElemente.getMain();
		 
		 m.getChildren().add(mediaView);
		 m.getChildren().add(ap);
		 
		
		 ap.setOpacity(0.0);
		 hb.setSpacing(10);
		 hb2.setSpacing(10);
		 ap.setBottomAnchor(hb, 20.0);
		 ap.setBottomAnchor(hb2, 20.0);
		 ap.setLeftAnchor(hb, 0.0);
		 ap.setRightAnchor(hb2, 0.0);
		 m.setBottomAnchor(ap, 0.0);
		 
		 
			mediaView.fitWidthProperty().bind(m.widthProperty());
			mediaView.fitHeightProperty().bind(m.heightProperty());
			mediaView.setPreserveRatio(false);
		 mediaPlayer.setAutoPlay(true);
		 ap.prefWidthProperty().bind(m.widthProperty());
		bgIV.fitWidthProperty().bind(m.widthProperty());
		bgIV.setFitHeight(100);
		
			Play.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						System.out.println("Eventtyp bei Play: " + e.getEventType());
						if (mediaPlayer.getStatus().toString()=="PLAYING"){
						System.out.println(mediaPlayer.getStatus());
						mediaPlayer.pause();
						Play.setText("");
						playIV.setImage(new Image(new File("Controls/play.png").toURI().toString()));
						}else{
						mediaPlayer.play();
						playIV.setImage(new Image(new File("Controls/pause.png").toURI().toString()));
					
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
						 if (stage.isFullScreen()){
							 stage.setFullScreen(false);
						 }else{
						 stage.setFullScreen(true);
						 }
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
						 GuiElemente.getMain().getChildren().remove(ap);
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
			ap.setOnMouseEntered(new EventHandler<MouseEvent>() {

		        @Override
		        public void handle(MouseEvent t) {
		        	Platform.runLater(new Runnable() {
						@Override
						public void run() {
							ap.setOpacity(0.5);
						}
					});
		           
		        }
		    });
			ap.setOnMouseExited(new EventHandler<MouseEvent>() {

		        @Override
		        public void handle(MouseEvent t) {
		        	Platform.runLater(new Runnable() {
						@Override
						public void run() {
							ap.setOpacity(0.0);
						}
					});
		        }
		    });
			
			
	 }
	 
	 
	 
	 
}