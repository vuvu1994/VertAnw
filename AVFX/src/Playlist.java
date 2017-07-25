import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

/**
 * Created by Denni on 28.05.2017.
 */
public class Playlist {
private String Name;
ObservableList MediaObjekte = FXCollections.observableArrayList();
ListView<String> lv;
Playlist pl;

private VBox vb;

    public Playlist(String Name){
        this.Name = Name;

        InternetBrowser.removeWebView();
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
        lv = new ListView<String>(MediaObjekte);
        vb.getChildren().add(lv);
        HBox hb = new HBox();
        Button b1 = new Button("Play");
        Button b2 = new Button("Hinzufügen");
        Button b3 = new Button ("Entfernen");
        Button b4 = new Button ("Alle Entfernen");
        hb.getChildren().add(b1);
        hb.getChildren().add(b2);
        hb.getChildren().add(b3);
        hb.getChildren().add(b4);
        vb.getChildren().add(hb);
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(2);
        try {
            File dir = new File("Playlist");
            if (!dir.exists()){
                dir.mkdir();
            }
            new File("Playlist/"+Name+".txt").createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    if (lv.getItems().size() > 0) {
                        ArrayList<String> al = new ArrayList<String>();
                        al.addAll(lv.getItems());
                        MediaPlayer.setFileinPlaylist(0);
                        MediaPlayer.createMediaPlayerwithPlaylist(al);
                    }
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
                    GuiElemente.getNavigationbar().getChildren().clear();
                    Button safe = new Button("Speichern");
                    safe.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {
                                System.out.println("safe");
                                ArrayList <String> safelist = new ArrayList<String>();
                                for (int i =0;i<lv.getItems().size();i++){
                                    safelist.add(lv.getItems().get(i));
                                }
                                writeToFile(safelist);
                                GuiElemente.getFlowPane().getChildren().clear();
                                GeneratePlaylist pl = new GeneratePlaylist();
                                pl.readfiles();
                                pl.create();
                                Status.make("Playlist gespeichert");
                            } catch (Exception e1) {

                                e1.printStackTrace();
                            }
                        }
                    });
                    Button extern = new Button("externe Datei hinzufügen");
                    extern.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            try {

                                FileChooser fileChooser = new FileChooser();
                                fileChooser.setTitle("Open Resource File");
                                FileChooser.ExtensionFilter extFilter =
                                        new FileChooser.ExtensionFilter("Media Datein", "*.mp4", "*.mp3", "*.wav", "*.flv");
                                fileChooser.getExtensionFilters().add(extFilter);
                                Scene scene =  (Scene) GuiElemente.getMain().getScene();
                                Stage stage = (Stage) scene.getWindow();
                                File file=fileChooser.showOpenDialog(stage);
                                lv.getItems().add(file.getAbsolutePath());
                            } catch (Exception e1) {

                                Status.make("Keine Datei ausgewählt");
                            }
                        }
                    });
                    GuiElemente.getNavigationbar().getChildren().add(safe);
                    GuiElemente.getNavigationbar().getChildren().add(extern);
                    new GenerateObjekts().start();
                } catch (Exception e1) {

                }
            }
        });
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {

                 lv.getItems().remove(lv.getSelectionModel().getSelectedIndex());
                    ArrayList <String> safelist = new ArrayList<String>();
                    for (int i =0;i<lv.getItems().size();i++){
                        safelist.add(lv.getItems().get(i));
                    }
                 writeToFile(safelist);
                } catch (Exception e1) {


                }
            }
        });
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    FileUtils.forceDelete(new File("Playlist\\" + Name+".txt"));
                    GuiElemente.getFlowPane().getChildren().clear();
                    GeneratePlaylist pl = new GeneratePlaylist();
                    pl.readfiles();
                    pl.create();
                } catch (Exception e1) {


                }
            }
        });
        setNavigationbar();

    }

    public static void setNavigationbar() {
        GuiElemente.getNavigationbar().getChildren().clear();
        Button newPlaylist = new Button("Neue Playlist");
        newPlaylist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    TextInputDialog dialog = new TextInputDialog("Playlist Name");
                    dialog.setTitle("neue Playlist");
                    dialog.setHeaderText("Name für Playlist eingeben");
                    dialog.setContentText("Name:");


                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                        Playlist n = new Playlist(result.get());
                        n.create();
                        n.draw();
                    }

                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        GuiElemente.getNavigationbar().getChildren().add(newPlaylist);
    }

    public void writeToFile(ArrayList<String> list) {
        try {
            FileUtils.forceDelete(new File("Playlist\\" + Name+".txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("Playlist\\" + Name+".txt"));
            for (String x : list) {
                writer.write(x);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {

        }
    }

    public void draw(){
        GuiElemente.getFlowPane().getChildren().add(vb);
    }

    public void add(String mo){
        lv.getItems().add(mo);
        lv.refresh();

    }
}
