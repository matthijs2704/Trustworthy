package com.Geisteskranken.Trustworthy;

import org.apache.logging.log4j.Logger;
import org.granitemc.granite.api.Granite;
import org.granitemc.granite.api.plugin.Plugin;
import org.granitemc.granite.api.plugin.OnEnable;
import org.granitemc.granite.api.plugin.PluginContainer;

@Plugin(name = "Trustworthy", id = "trustworthy", version = "1.0")
public class Trustworthy extends Thread {
	public static String CheckLogin = "true";
	public static Logger logger;
	public static PluginContainer plugin;
	public static String Name = "§d[Trustworthy] ";

	public Trustworthy() {
		plugin = Granite.getPluginContainer(this);
		plugin.registerEventHandler(new TrustworthyEvent());
		plugin.registerCommandHandler(new TrustworthyEvent());
		logger = Granite.getLogger();
		if (TrustworthyConfig.readConfig()) {
			logger.info(Name + "Everything appears OK");
		} else {
			// TODO
			// Something didn't read OK, probably not an issue, most likely
			// outcome is that they config file did not exist but was created.
		}
	}

	@OnEnable
	public void OnEnable(PluginContainer p) {
		//TODO
		//Nothing seems to get executed from down here. Strange.
	}
}
