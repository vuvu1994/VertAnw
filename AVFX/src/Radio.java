import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;


/**
 * Created by Vural on 30.05.2017.
 */
public class Radio {



    public static void createRadioElements(){
        FlowPane fp = GuiElemente.getFlowPane();

        HBox hbox = new HBox();
        hbox.setSpacing(50);
        hbox.setStyle("-fx-background-color: black;");
        hbox.setPadding(new Insets(300, 0, 0, 200));
        hbox.setPrefWidth(600);
        hbox.setPrefHeight(400);

        Button buttonRadioPlay = new Button("Play");
        buttonRadioPlay.setPrefSize(100, 20);

        Button buttonRadioStop = new Button("Stop");
        buttonRadioStop.setPrefSize(100, 20);
        hbox.getChildren().addAll(buttonRadioPlay, buttonRadioStop);

        fp.getChildren().add(hbox);

        RadioFunction rf = new RadioFunction();

        buttonRadioPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                rf.start();
            }
        });

        buttonRadioStop.setOnAction(new EventHandler<ActionEvent>()  {
            @Override
            public void handle(ActionEvent event) {

            RadioFunction.player.close();
            }
        });
    }

}






