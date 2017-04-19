import javafx.scene.layout.AnchorPane;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;import javafx.scene.layout.FlowPane;
public class GuiElemente {
  static AnchorPane Main;
  static ScrollPane ScrollPane;
  static FlowPane FlowPane;
  public static void setMain(AnchorPane main){
	  Main = main;
  }
  public static void setScrollPane(ScrollPane scrollpane){
	  ScrollPane = scrollpane;
  }
  public static void setFlowPane(FlowPane flowpane){
	  FlowPane = flowpane;
  }
  public static ScrollPane getScrollPane(){
	  return ScrollPane;
  }
  public static FlowPane getFlowPane(){
	  return FlowPane;
  }
}
