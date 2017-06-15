import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class InternetBrowser {
	static WebView myWebView = new WebView();
	static WebEngine engine = myWebView.getEngine();
	static ScrollPane sp = new ScrollPane();
	static VBox vb = new VBox();
	public static void createWebView() {

		engine.load("http://www.youtube.de");

		vb.getChildren().addAll(myWebView);
		sp = GuiElemente.getScrollPane();
		sp.setContent(vb);
		myWebView.prefHeightProperty().bind(sp.heightProperty());
		myWebView.prefWidthProperty().bind(sp.widthProperty());


	}

	public static void closeWebview(){
		vb.getChildren().clear();
	}
}