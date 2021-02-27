package org.ramanuj.factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class AppFactory {

	private static AppFactory factory;

	private String helpTtext;

	private AppFactory() {
		init();
	}

	public void init() {
		System.out.println("Initializing factory");
		// readHelpText();
		readText2();
	}

	public static AppFactory getInstance() {
		if (factory == null) {
			factory = new AppFactory();
		}

		return factory;
	}

	private void readHelpText() {

		InputStream is = null;

		try {
			is = AppFactory.class.getResourceAsStream("/help.html");

			// helpTtext = Jsoup.parse(is, "UTF-8", "").toString();

			helpTtext = IOUtils.toString(is, StandardCharsets.UTF_8);

			helpTtext = helpTtext.trim();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void readText2() {

		StringBuilder contentBuilder = new StringBuilder();

		InputStream is = null;

		try {

			is = AppFactory.class.getResourceAsStream("/help.html");

			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String str;
			while ((str = br.readLine()) != null) {
				contentBuilder.append(str);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		helpTtext = contentBuilder.toString();
		//System.out.println(helpTtext);
	}

	/**
	 * get Help Text
	 * 
	 * @return String
	 */
	public String getHelpText() {
		if (StringUtils.isEmpty(helpTtext)) {
			return "You are on your own, Help not available";
		}
		return helpTtext;
	}

}
