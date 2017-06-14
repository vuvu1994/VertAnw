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

	public static void createWebView() {

		engine.load("http://www.youtube.de");

		VBox Vb = new VBox();
		//Vb.setAlignment(Pos.CENTER);
		Vb.getChildren().addAll(myWebView);
		FlowPane fp = GuiElemente.getFlowPane();
		myWebView.prefWidthProperty().bind(fp.widthProperty());
		myWebView.prefHeightProperty().bind(fp.heightProperty());

		fp.getChildren().clear();
		fp.getChildren().add(Vb);

	}
}