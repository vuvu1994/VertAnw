import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javafx.application.Platform;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

public class GenerateObjekts extends Thread {

	MediaObjekt m=null;
	private ArrayList<AnchorPane> Objekte = new ArrayList<AnchorPane>();
	public GenerateObjekts(){
		
	}
	public void run(){
		try {
			generate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generate() throws IOException, URISyntaxException{
		FlowPane fp = GuiElemente.getFlowPane();
				for (int i = 0; i < 20; i++) {
					m = new MediaObjekt();
					m.setName("Ghost in the Shell");
					
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
