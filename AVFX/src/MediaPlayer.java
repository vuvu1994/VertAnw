import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
import javafx.util.Duration;
import java.sql.SQLException;

public class MediaPlayer {

	 static MediaView mediaView;
	 static Media media;
	 static javafx.scene.media.MediaPlayer mediaPlayer;
	 private static Duration duration;
	 static private Slider dauer;
	 static private Slider volumeSlider;
	 static boolean backpressed = false;
	 static ArrayList<String> playlist;
	 static boolean playlistactive = false;
	 static int FileinPlaylist = 0;
	static boolean started =false;
	static String mediaName;
	static char charMediaName;

	 public static void createMediaPlayerwithPlaylist(ArrayList al){
	 	playlist = al;
	 	playlistactive=true;
		 media = new Media(new File(playlist.get(FileinPlaylist)).toURI().toString());
		 try {
			 createElements();
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 FileinPlaylist++;
	 }
	 public static void createMediaPlayer(String datei){

		 media = new Media(new File(datei).toURI().toString());






		 try {
			 createElements();
			 try {
				 System.out.println(media.getSource());
				 Database.addtoMedia(media.getSource());
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
	 }


	 public static void createMediaPlayerwithURL(String datei){
		 Service<Void> service = new Service<Void>() {
			 @Override
			 protected Task<Void> createTask() {
				 return new Task<Void>() {
					 @Override
					 protected Void call() throws Exception {
						 //Background work
						 final CountDownLatch latch = new CountDownLatch(1);
						 Platform.runLater(new Runnable() {
							 @Override
							 public void run() {
								 try{
									 //FX Stuff done here
									 media = new Media(datei);
									 createElements();
								 } catch (SQLException e) {
									 e.printStackTrace();
								 } finally{
									 latch.countDown();
								 }
							 }
						 });
						 latch.await();
						 //Keep with the background work
						 return null;
					 }
				 };
			 }
		 };
		 service.start();


		 }

		 public static void createRadio(String live){
			 media = new Media(live);
			 mediaPlayer = new javafx.scene.media.MediaPlayer(media);
			 mediaView = new MediaView(mediaPlayer);
			 AnchorPane m = GuiElemente.getMain();
			 m.getChildren().add(mediaView);
			 mediaView.fitWidthProperty().bind(m.widthProperty());
			 mediaView.fitHeightProperty().bind(m.heightProperty());
			 mediaView.setPreserveRatio(false);
			 mediaPlayer.setAutoPlay(false);
			 Platform.runLater(new Runnable() {
				 @Override
				 public void run() {

					 GuiElemente.gethbox().setVisible(false);
					 GuiElemente.getanchorpane().setVisible(false);
					 GuiElemente.getvbox().setVisible(false);
				 }
			 });
			mediaPlayer.onReadyProperty().set(() ->mediaPlayer.play());
		 }
	 public static void createElements() throws SQLException {
		 	InternetBrowser.removeWebView();
		 	started = false;
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
		 Play.setStyle("-fx-background-color: transparent;");
		 Image backI=new Image(new File("Controls/back.png").toURI().toString());
		 ImageView backIV=new ImageView(backI);
		 Button back = new Button("",backIV);
		 back.setStyle("-fx-background-color: transparent;");
		 Image forwardI=new Image(new File("Controls/forward.png").toURI().toString());
		 ImageView forwardIV=new ImageView(forwardI);
		 Button forward = new Button("",forwardIV);
		 forward.setStyle("-fx-background-color: transparent;");
		 dauer = new Slider();
		 dauer.setPrefWidth(500);
		 volumeSlider = new Slider();
		 Image fullI=new Image(new File("Controls/fullscreen.png").toURI().toString());
		 ImageView fullIV=new ImageView(fullI);
		 Button fullscreen = new Button("",fullIV);
		 fullscreen.setStyle("-fx-background-color: transparent;");
		 Image endeI=new Image(new File("Controls/exit.png").toURI().toString());
		 ImageView endeIV=new ImageView(endeI);
		 Button ende = new Button("",endeIV);
		 ende.setStyle("-fx-background-color: transparent;");
		 hb.getChildren().add(Play);
		 hb.getChildren().add(back);
		 hb.getChildren().add(forward);
		 hb.getChildren().add(dauer);
		 hb2.getChildren().add(fullscreen);
		 hb2.getChildren().add(ende);
		 hb2.getChildren().add(volumeSlider);
		 
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
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			 @Override
			 public void run() {
				 Database.updateaktuell(media.getSource(), null);

				 if (playlistactive&& playlist.size()>FileinPlaylist) {
					 mediaPlayer.dispose();
					 GuiElemente.getMain().getChildren().remove(mediaView);
					 GuiElemente.getMain().getChildren().remove(ap);
					 createMediaPlayer(playlist.get(FileinPlaylist));
					 FileinPlaylist++;
				 }
			 }
		 });
		
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
						//GuiElemente.getRadiostream().kill();
						System.out.println("Eventtyp bei ende: " + e.getEventType());
						mediaPlayer.stop();
						mediaView.setMediaPlayer(null);
						  mediaPlayer.dispose();
						System.gc();
						mediaPlayer.dispose();
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
							if(GuiElemente.isLivestream()){

							}
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
			forward.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						if (mediaPlayer.getRate() > 1){
							mediaPlayer.setRate(1.0);
						}else {
						mediaPlayer.setRate(4.0);
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			mediaPlayer.setOnReady(new Runnable() {
	            public void run() {
	                duration = mediaPlayer.getMedia().getDuration();
	                updateValues();
	            }
	        });
			
			
			
			mediaPlayer.currentTimeProperty().addListener(new InvalidationListener() 
	        {
			
				@Override
				public void invalidated(javafx.beans.Observable observable) {
					// TODO Auto-generated method stub
					 updateValues();
				}

				
	        });
			back.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (!backpressed){
	            		backpressed = true;
	            	}else{
	            		backpressed = false;
	            	}
					 Task task = new Task<Void>() {
				            protected Void call() throws Exception {
				            	
				            	while (backpressed){
				            	double currentzeit = mediaPlayer.getCurrentTime().toSeconds();
								currentzeit = currentzeit - 0.1;
								mediaPlayer.seek(Duration.seconds(currentzeit));
				                
				            	}
								return null;
				            }
				        };
				        Thread th = new Thread(task);
				        th.setDaemon(true);
				        th.start();
					
					
				}
			});
			
			dauer.valueProperty().addListener(new InvalidationListener() {

				@Override
				public void invalidated(javafx.beans.Observable observable) {
					// TODO Auto-generated method stub
					  if (dauer.isValueChanging()) {
					       // multiply duration by percentage calculated by slider position
					          mediaPlayer.seek(duration.multiply(dauer.getValue() / 100.0));
					       }
				}
			});
			
			volumeSlider.valueProperty().addListener(new InvalidationListener() {
			    

				@Override
				public void invalidated(javafx.beans.Observable observable) {
					// TODO Auto-generated method stub
					 if (volumeSlider.isValueChanging()) {
				           mediaPlayer.setVolume(volumeSlider.getValue() / 100.0);
				       }
				}
			});



	 }
	 
	 public static void updateValues() {
		  
	     Platform.runLater(new Runnable() {
	        public void run() {
	          Duration currentTime = mediaPlayer.getCurrentTime();
	          if (!started) {
	          	started = true;
				  String Stringstartzeit = null;
				  try {
					  Stringstartzeit = Database.getaktuell(media.getSource());
				  } catch (SQLException e) {
					  e.printStackTrace();
				  }
				  System.out.println("Startzeit " + Stringstartzeit);
				  Double startzeitDouble = Double.parseDouble(Stringstartzeit);
				  mediaPlayer.seek(Duration.seconds(startzeitDouble));
				  System.out.println("WHY THE FUCK");
			  }
				Database.updateaktuell(media.getSource(), currentTime.toSeconds());




	          dauer.setDisable(duration.isUnknown());
	          if (!dauer.isDisabled() 
	            && duration.greaterThan(Duration.ZERO) 
	            && !dauer.isValueChanging()) {
	             dauer.setValue(currentTime.divide(duration).toMillis()
	                  * 100.0);
	          }
	          if (!volumeSlider.isValueChanging()) {
	            volumeSlider.setValue((int)Math.round(mediaPlayer.getVolume() 
	                  * 100));
         }
	        }
	     });
	  
	}
	 
	 
}
