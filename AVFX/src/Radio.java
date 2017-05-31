import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import java.io.File;

/**
 * Created by Vural on 30.05.2017.
 */
public class Radio {



    public static void createRadioElements(){
        FlowPane fp = GuiElemente.getFlowPane();

        HBox hbox = new HBox();
        hbox.setSpacing(50);
        hbox.setStyle("-fx-background-color: black;");
        hbox.setPadding(new Insets(300,0,0,200));
        hbox.setPrefWidth(600);
        hbox.setPrefHeight(400);

        Button buttonRadioPlay = new Button("Play");
        buttonRadioPlay.setPrefSize(100, 20);

        Button buttonRadioStop = new Button("Stop");
        buttonRadioStop.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonRadioPlay, buttonRadioStop);

        fp.getChildren().add(hbox);




        buttonRadioPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try
                {
                    playRadioStream ("http://wdr-wdr5-live.icecast.wdr.de/wdr/wdr5/live/mp3/128/stream.mp3");
                }
                catch ( IOException e )
                {
                    e.printStackTrace ();
                }
                catch ( JavaLayerException e )
                {
                    e.printStackTrace ();
                }
            }
        });

        buttonRadioStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
    private static void playRadioStream (String spec) throws IOException, JavaLayerException
    {
        // Connection
        URLConnection urlConnection = new URL (spec).openConnection ();

        urlConnection.connect ();

        // Playing
        Player player = new Player ( urlConnection.getInputStream () );
        player.play ();
    }

}
