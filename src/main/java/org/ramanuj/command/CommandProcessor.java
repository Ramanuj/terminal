package org.ramanuj.command;

import org.apache.commons.lang3.StringUtils;
import org.ramanuj.terminal.common.AppConstants;
import org.ramanuj.terminal.util.AppUtil;
import org.ramanuj.terminal.util.ConsoleUtil;

import javafx.scene.web.WebEngine;

public class CommandProcessor {

	public void processCommand(WebEngine webEngine, String commandStr) {

		if (StringUtils.isEmpty(commandStr)) {
			// no command entered, present a help text
			ConsoleUtil.presentHelpText(webEngine);
		} else {
			this.processCommandEntered(webEngine, commandStr);
		}
	}

	/**
	 * process the commands entered
	 * 
	 * @param webEngine
	 * @param commandStr
	 */
	private void processCommandEntered(WebEngine webEngine, String commandStr) {

		try {
			Command command = Enum.valueOf(Command.class, commandStr);
			System.out.println(command.toString());
			
			switch (command) {
			case quit:
				AppUtil.closeApp();
				break;
			case exit:
				AppUtil.closeApp();
				break;
			case help:
				ConsoleUtil.presentHelpText(webEngine);
				break;
			case clear:
				ConsoleUtil.clearConsole(webEngine);
				break;
			case pwd:
				System.out.println("current working directory");
				ConsoleUtil.clearConsole(webEngine);
				break;
			default:
				// Command not recognized
				ConsoleUtil.appendText(webEngine, AppConstants.COMMAND_NOT_RECOGNISED);
				ConsoleUtil.presentHelpText(webEngine);
				break;
			}
		} catch (Exception e) {
			ConsoleUtil.appendText(webEngine, AppConstants.COMMAND_NOT_RECOGNISED);
			ConsoleUtil.presentHelpText(webEngine);
			e.printStackTrace();
		}

	}

}
