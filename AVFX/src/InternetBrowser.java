import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

		engine.load("https://www.youtube.com");

		VBox Vb = new VBox();

		Vb.getChildren().addAll(myWebView);
		FlowPane fp = GuiElemente.getFlowPane();
		myWebView.setPrefWidth(fp.getPrefWidth() - 30);
		myWebView.setPrefHeight(fp.getPrefHeight() - 20);
		fp.getChildren().clear();
		fp.getChildren().add(Vb);

	}
}