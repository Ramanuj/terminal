package org.ramanuj.terminal.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.ramanuj.factory.AppFactory;
import org.ramanuj.terminal.SystemInfo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;

/**
 * Utility
 * 
 * @author sriva
 *
 */
public class ConsoleUtil {

	private final static AppFactory factory = AppFactory.getInstance();

	public static void appendText(WebEngine webEngine, String message) {
		webEngine.executeScript(String.format("appendtext('%s')", message));
	}

	public static void clearTextFieldAfterEnter(TextField commandBox) {
		commandBox.setText("");
	}

	/**
	 * Get an initial message on console
	 * 
	 * @param consoleArea
	 */
	public static String getInitialMessage() {

		var javaVersion = SystemInfo.javaVersion();
		var javafxVersion = SystemInfo.javafxVersion();

		StringBuilder initialMessage = new StringBuilder();
		initialMessage.append("Welcome to Ramanuj&#39;s Terminal");
		initialMessage
				.append("<br>You are using JavaFX " + javafxVersion + ", running " + "on Java " + javaVersion + ".");
		initialMessage.append("<br>Current Working Directory -> " + SystemInfo.currentUserDir().replace('\\', '/'));
		initialMessage.append("<br>User Home Directory -> " + SystemInfo.userHome().replace('\\', '/'));
		initialMessage.append("<br>User Name -> " + SystemInfo.userName());
		initialMessage.append("<br><br>What should I do for you today " + SystemInfo.userName());

		return initialMessage.toString();
	}

	public static void addListener(WebEngine webEngine) {

		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			public void changed(ObservableValue ov, State oldState, State newState) {
				if (newState == State.SUCCEEDED) {
					webEngine.executeScript(String.format("appendtext('%s')", getInitialMessage()));
				}
			}
		});
	}

	/**
	 * Prints Help Text
	 * 
	 * @param webEngine
	 */
	public static void presentHelpText(WebEngine webEngine) {
		webEngine.executeScript(String.format("appendtext('%s')", factory.getHelpText()));
	}
	
	/**
	 * Clears console
	 * 
	 * @param webEngine
	 */
	public static void clearConsole(WebEngine webEngine) {
		webEngine.executeScript("clearConsole()");
	}

}
