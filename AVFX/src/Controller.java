import java.awt.FlowLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Controller {
@FXML
ImageView bimage;
@FXML
AnchorPane Main;
@FXML
AnchorPane anchorpane;
@FXML
ScrollPane ScrollPane;
@FXML
FlowPane fp;
@FXML
VBox vbox;
@FXML
HBox hbox;
	public void initialize() throws InterruptedException, SQLException {
		Settings.createProperties();
		fp.prefWidthProperty().bind(ScrollPane.widthProperty());
		fp.prefHeightProperty().bind(ScrollPane.heightProperty());
		fp.setStyle("-fx-background-color: transparent;");
		ScrollPane.setStyle("-fx-background-color: transparent;");

		GuiElemente.setScrollPane(ScrollPane);
		GuiElemente.setFlowPane(fp);
		GuiElemente.setMain(Main);
		GuiElemente.setanchorpane(anchorpane);
		GuiElemente.sethbox(hbox);
		GuiElemente.setvbox(vbox);
		bimage.fitWidthProperty().bind(Main.widthProperty());
		bimage.fitHeightProperty().bind(Main.heightProperty());
		bimage.setPreserveRatio(false);
		//Database test
		Database.getDatabase();
		Database.createTable();
		Database.addtoMedia("TestName");
		Database.addtoMedia("TestName2");
		System.out.println(Database.getAllMedia());
		//Database test Ende
	}
  public void bibliothek() throws Exception{
	  fp.getChildren().clear();
	  new GenerateObjekts().start();
	  
  }
  public void exit(){
	  System.out.println("EXIT");
	  System.exit(0);
  }
	
}
