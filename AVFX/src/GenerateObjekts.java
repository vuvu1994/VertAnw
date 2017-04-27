import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class GenerateObjekts extends Thread {
	String[] MediaNamen;
	MediaObjekt m=null;
	private ArrayList<AnchorPane> Objekte = new ArrayList<AnchorPane>();
	public GenerateObjekts(){
		
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
	public void getMedia(){
		File file = new File("Z:/Movies");
		MediaNamen = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isFile();
			}
		});
		System.out.println(MediaNamen[0].toString());
	}
	
	public void generate() throws IOException, URISyntaxException{
		FlowPane fp = GuiElemente.getFlowPane();
				for (int i = 0; i < MediaNamen.length; i++) {
					m = new MediaObjekt();
					m.setName(MediaNamen[i]);
					m.setBild("G:/Bilder/ghost.jpg");
					m.setPath("");
					m.createMediaObjekt();
					
					Objekte.add(m.getObjekt());
			
				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						
						System.out.println(Objekte.size());
						
						fp.getChildren().addAll(Objekte);
				
					}
				});
	}
}
