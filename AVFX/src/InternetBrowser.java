import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
		Button zurueck = new Button("Zur�ck");
		Button vorwaerts = new Button("Vorw�rts");
		GuiElemente.getNavigationbar().getChildren().clear();
		GuiElemente.getNavigationbar().getChildren().add(zurueck);
		GuiElemente.getNavigationbar().getChildren().add(vorwaerts);
		InternetBrowser.removeWebView();
		GuiElemente.setYoutube(true);
		engine.load("http://www.youtube.de");
		GuiElemente.getanchorpane().getChildren().add(myWebView);
		myWebView.prefHeightProperty().bind(GuiElemente.getanchorpane().heightProperty());
		myWebView.prefWidthProperty().bind(GuiElemente.getanchorpane().widthProperty());

		zurueck.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Platform.runLater(() -> {
					InternetBrowser.engine.executeScript("history.back()");
				});
			}
		});

		vorwaerts.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Platform.runLater(() -> {
					InternetBrowser.engine.executeScript("history.forward()");
				});
			}
		});

	}

	public static void removeWebView(){
		if (GuiElemente.getYoutube()) {
			myWebView.getEngine().load(null);
			GuiElemente.getanchorpane().getChildren().remove(myWebView);
		}
	}

}