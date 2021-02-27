package org.ramanuj.terminal;

public class SystemInfo {

	public static String javaVersion() {
		return System.getProperty("java.version");
	}

	public static String javafxVersion() {
		return System.getProperty("javafx.version");
	}

	public static String currentUserDir() {
		return System.getProperty("user.dir");
	}

	public static String userHome() {
		return System.getProperty("user.home");
	}

	public static String userName() {
		return System.getProperty("user.name");
	}

}