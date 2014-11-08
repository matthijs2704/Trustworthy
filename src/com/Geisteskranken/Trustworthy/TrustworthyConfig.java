package com.Geisteskranken.Trustworthy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import com.Geisteskranken.Trustworthy.Trustworthy;

public class TrustworthyConfig {

	private static File f;
	static Properties prop = new Properties();
	static OutputStream output = null;

	public static boolean readConfig() {

		f = new File("plugins/Trustworthy/Trustworthy.conf");
		Properties prop = new Properties();
		InputStream input = null;

		if (f.exists() && !f.isDirectory()) {
			try {
				input = new FileInputStream(f);

				prop.load(input);

				Trustworthy.CheckLogin = prop.getProperty("CheckLogin");
				// TODO
				// Trustworthy.announce = prop.getProperty("CheckLoginOnce");

			} catch (IOException ex) {
				Trustworthy.logger.warn(Trustworthy.Name
						+ "Disabled! Configuration error.", ex);
				Trustworthy.plugin.disable();
			}
			try {
				input.close();
			} catch (IOException e) {
				Trustworthy.logger.warn(Trustworthy.Name
						+ "Disabled! Configuration error.", e);
				Trustworthy.plugin.disable();
			}

			Trustworthy.logger.info(Trustworthy.Name + "Config: OK");
			return true;
		} else {
			createConfig();
			return false;
		}

	}

	public static void createConfig() {
		try {

			f.getParentFile().mkdirs();
			f.createNewFile();
			output = new FileOutputStream(f);

			prop.setProperty("CheckLogin", "true");
			// TODO
			// prop.setProperty("CheckLoginOnce", "false");
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
					Trustworthy.logger.info(Trustworthy.Name
							+ "Configuration file created.");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

}
