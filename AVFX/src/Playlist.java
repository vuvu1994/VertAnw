import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

/**
 * Created by Denni on 28.05.2017.
 */
public class Playlist {
private String Name;
ObservableList MediaObjekte = FXCollections.observableArrayList();
ListView lv;
Playlist pl;

private VBox vb;

    public Playlist(String Name){
        this.Name = Name;


    }

    public void create(){
        pl = this;
        vb = new VBox();
        vb.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
        Text name = new Text();
        name.setText(Name);
        name.setWrappingWidth(300);
        name.setFont(Font.font("Verdana", 20));
        name.setFill(Color.WHITE);
        vb.getChildren().add(name);
        lv = new ListView(MediaObjekte);
        vb.getChildren().add(lv);
        HBox hb = new HBox();
        Button b1 = new Button("Play");
        Button b2 = new Button("Hinzufügen");
        Button b3 = new Button ("Entfernen");
        hb.getChildren().add(b1);
        hb.getChildren().add(b2);
        hb.getChildren().add(b3);
        vb.getChildren().add(hb);
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    ArrayList al = new ArrayList();
                    al.addAll(lv.getItems());
                   MediaPlayer.createMediaPlayerwithPlaylist(al);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    GuiElemente.setPlaylist(pl);
                    GuiElemente.Playlistactivated(true);
                    GuiElemente.getFlowPane().getChildren().clear();
                    new GenerateObjekts().start();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }

    public void draw(){
        GuiElemente.getFlowPane().getChildren().add(vb);
    }

    public void add(String mo){
        lv.getItems().add(mo);
        lv.refresh();

    }
}
