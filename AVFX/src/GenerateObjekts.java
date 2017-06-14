import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.apache.commons.io.FileUtils;

public class GenerateObjekts extends Thread {
	String[] MediaNamen;
	ArrayList Media = new ArrayList();
	MediaObjekt m=null;
	int whichpath = 0;
	ArrayList path = new ArrayList();

;	private ArrayList<AnchorPane> Objekte = new ArrayList<AnchorPane>();
	public GenerateObjekts(){
		if (!GuiElemente.playlist) {
			GuiElemente.getNavigationbar().getChildren().clear();
			Button Video = new Button("Video");
			Button Audio = new Button("Audio");
			Video.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						whichpath = 0;
						MediaNamen = null;
						Media.clear();
						MediaObjekt m=null;
						path.clear();
						GuiElemente.getFlowPane().getChildren().clear();
						getMedia();
						generate();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			Audio.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						whichpath = 1;
						MediaNamen = null;
						Media.clear();
						MediaObjekt m=null;
						path.clear();
						GuiElemente.getFlowPane().getChildren().clear();
						getMedia();
						generate();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			GuiElemente.getNavigationbar().getChildren().add(Video);
			GuiElemente.getNavigationbar().getChildren().add(Audio);
		}
	}
	public void run(){
		try {
			getMedia();
			generate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getMedia() throws IOException {
		//Wird später benötigt für die Rückgabe einer ArrayList
		Settings list = new Settings();
		// In al befinden sich alle Pfade die die Arraylist beeinhaltet
		path.clear();
		if (whichpath == 0 ) {
			path = list.readDirectories("Mediathek");
		}else{
			path= list.readDirectories("Audiothek");
		}
		Media.clear();
		Objekte.clear();
		for (int i =0;i<path.size();i++) {
			File file = new File(path.get(i).toString());
			MediaNamen = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File current, String name) {
					return new File(current, name).isFile();
				}
			});
			System.out.println("Soviel: "+MediaNamen.length);
			for (int j=0;j<MediaNamen.length;j++) {
				Media.add(MediaNamen[j].toString());
				m = new MediaObjekt();
				m.setName(MediaNamen[j].toString());
				m.setBild("Cover/"+MediaNamen[j].toString()+".jpg");
				m.setPath(path.get(i)+"/");
				m.createMediaObjekt();
				Objekte.add(m.getObjekt());
			}
			System.out.println("Soviel Media: "+Media.size());
		}
	}
	
	public void generate() throws IOException, URISyntaxException{
				FlowPane fp = GuiElemente.getFlowPane();

//				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						fp.getChildren().clear();
						System.out.println(Objekte.size());
						
						fp.getChildren().addAll(Objekte);
				
					}
				});
	}
}
