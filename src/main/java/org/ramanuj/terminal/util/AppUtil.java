/**
 * 
 */
package org.ramanuj.terminal.util;

import javafx.application.Platform;

/**
 * @author sriva
 *
 */
public class AppUtil {

	public static void closeApp() {
		System.out.println("Calling Platform.exit():");
		Platform.exit();
		System.out.println("Calling System.exit(0):");
		System.exit(0);
	}

}
