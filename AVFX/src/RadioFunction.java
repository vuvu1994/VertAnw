import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Vural on 31.05.2017.
 */
public class RadioFunction extends Thread{
        static AdvancedPlayer player;
        static String spec = "";

    public synchronized void run() {

       try{

           if (Controller.comboBoxValue == null) {
               Radio.playerstarted = false;
               System.out.println(Controller.comboBoxValue);
           } else if (Controller.comboBoxValue == "1Live") {
               spec = Settings.getText1live();
           } else if (Controller.comboBoxValue == "WDR2") {
               spec = Settings.getTextWdr2();
           } else if (Controller.comboBoxValue == "WDR5") {
               spec = Settings.getTextWdr5();
           }

            // Connection
           URLConnection urlConnection = new URL(spec).openConnection();

           urlConnection.connect();

           // Playing

           player = new AdvancedPlayer(urlConnection.getInputStream());
           player.play();

       }catch (IOException e){

       }catch (JavaLayerException e){

       }
    }
    public void close(){
    player.close();
    }


}
