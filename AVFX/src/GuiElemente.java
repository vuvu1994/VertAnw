import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
public class GuiElemente {
  static AnchorPane Main;
  static ScrollPane ScrollPane;
  static FlowPane FlowPane;
  static AnchorPane anchorpane;
  static HBox hbox;
  static VBox vbox;

  public static ArrayList MediaDatein = new ArrayList();
  public static void setMain(AnchorPane main){
	  Main = main;
  }
  public static AnchorPane getMain(){
	  return Main;
  }
  public static AnchorPane getanchorpane(){
	  return anchorpane;
  }
  public static VBox getvbox(){
	  return vbox;
  }
  public static HBox gethbox(){
	  return hbox;
  }
  public static void setScrollPane(ScrollPane scrollpane){
	  ScrollPane = scrollpane;
  }
  public static void setFlowPane(FlowPane flowpane){
	  FlowPane = flowpane;
  }
  public static void sethbox(HBox hb){
	  hbox = hb;
  }
  public static void setvbox(VBox vb){
	  vbox = vb;
  }
  public static void setanchorpane(AnchorPane ap){
	  anchorpane = ap;
  }
  public static ScrollPane getScrollPane(){
	  return ScrollPane;
  }
  public static FlowPane getFlowPane(){
	  return FlowPane;
  }
  public static void setMediaDatein(ArrayList list){
	  MediaDatein = list;
  }
  public ArrayList getMediaDatein(){
	  return MediaDatein;
  }
}
