package org.ramanuj.terminal;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.jsoup.Jsoup;
import org.ramanuj.command.CommandProcessor;
import org.ramanuj.factory.AppFactory;
import org.ramanuj.terminal.util.ConsoleUtil;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * JavaFX App
 */
public class TerminalApp extends Application {

	private final AppFactory factory = AppFactory.getInstance();

	private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

	private TextField commandBox = new TextField();
	private WebView webView = new WebView();
	private WebEngine webEngine = webView.getEngine();

	private Label prompt;

	private Font globalFont;

	private CommandProcessor commandProcessor = new CommandProcessor();

	@Override
	public void start(Stage stage) {

		this.initComponents();

		var scene = new Scene(this.getPane());
		commandBox.requestFocus();

		scene.getStylesheets().add("app.css");

		this.setStageProperties(stage);

		stage.setScene(scene);
		stage.show();
	}

	private void initComponents() {
		this.initApp();
		this.initConsolArea();
		this.initCommandBox();
	}

	private void initCommandBox() {
		commandBox.setFont(globalFont);

		commandBox.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {

				String command = commandBox.getText();

				ConsoleUtil.appendText(webEngine, command);
				ConsoleUtil.clearTextFieldAfterEnter(commandBox);

				commandProcessor.processCommand(webEngine, command);
			}
		});
	}

	private HBox commandPane() {

		HBox box = new HBox();
		box.setId("bottombox");

		box.getChildren().add(prompt);
		box.getChildren().add(commandBox);

		prompt.setPrefWidth(50.0);
		prompt.setFont(globalFont);
		commandBox.setPrefWidth(primaryScreenBounds.getWidth());
		box.setMaxWidth(primaryScreenBounds.getWidth());

		box.setAlignment(Pos.CENTER_RIGHT);

		return box;

	}

	private void initConsolArea() {
		URL url = this.getClass().getResource("/console.html");
		webEngine.load(url.toString());
		ConsoleUtil.addListener(webEngine);
	}

	private void initApp() {
		globalFont = Font.font("Consolas", FontWeight.NORMAL, 14);
		prompt = new Label(" >");
	}

	private void setStageProperties(Stage stage) {

		stage.setTitle("Terminal");

		stage.setMaximized(true);

		stage.initStyle(StageStyle.TRANSPARENT);

		stage.setFullScreen(false);

		// set Stage boundaries to visible bounds of the main screen
		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(primaryScreenBounds.getWidth());
		stage.setHeight(primaryScreenBounds.getHeight());
	}

	public BorderPane getPane() {
		BorderPane borderPane = new BorderPane();

		borderPane.setCenter(webView);

		borderPane.setBottom(this.commandPane());

		return borderPane;
	}

	public static void main(String[] args) {
		launch();
	}

}