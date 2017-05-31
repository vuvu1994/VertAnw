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


    public synchronized void run() {
       try{

           // Connection
           URLConnection urlConnection = new URL("http://wdr-wdr5-live.icecast.wdr.de/wdr/wdr5/live/mp3/128/stream.mp3").openConnection();

           urlConnection.connect();

           // Playing

           player = new AdvancedPlayer(urlConnection.getInputStream());
           player.play();

       }catch (IOException e){

       }catch (JavaLayerException e){

       }
    }



}
