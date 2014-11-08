package com.Geisteskranken.Trustworthy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.granitemc.granite.api.Granite;
import org.granitemc.granite.api.command.Command;
import org.granitemc.granite.api.command.CommandInfo;
import org.granitemc.granite.api.entity.player.Player;
import org.granitemc.granite.api.event.On;
import org.granitemc.granite.api.event.player.EventPlayerJoin;

public class TrustworthyEvent {

	public static final ExecutorService EventQueue = Executors
			.newFixedThreadPool(2);

	@On(event = EventPlayerJoin.class)
	public void event(EventPlayerJoin evt) {
		if(Trustworthy.CheckLogin.equals("true")){
		EventQueue.execute(new Runnable() {
			public void run() {
				String playa = evt.getPlayer().getName();
				int MCBANS = TrustworthyStats.getStats(playa, "mcbans");
				int MCBOUNCER = TrustworthyStats.getStats(playa, "mcbouncer");
				int MCBLOCKIT = TrustworthyStats.getStats(playa, "mcblockit");
				int MINEBANS = TrustworthyStats.getStats(playa, "minebans");
				int GLIZER = TrustworthyStats.getStats(playa, "glizer");

				if (!((MCBANS + MCBOUNCER + MCBLOCKIT + MINEBANS + GLIZER) == 0)) {
					for (Player p : Granite.getServer().getPlayers()) {
						p.sendMessage(Trustworthy.Name
								+ "§cPlayer '"
								+ playa
								+ "'"
								+ " has "
								+ String.valueOf(MCBANS + MCBOUNCER + MCBLOCKIT
										+ MINEBANS + GLIZER)
								+ " bans on record.");
					}
				} else {
					for (Player p : Granite.getServer().getPlayers()) {
						p.sendMessage(Trustworthy.Name + "§aPlayer '" + playa
								+ "' has no bans on records.");
					}
				}

			}
		});
		}

	}

	@Command(name = "Trustworthy", info = "Used to lookup global ban records on a player. Syntax: /Trustworthy [Playername]", aliases = { "tw" })
	public void TrustworthyCommand(CommandInfo info) {
		if (!(info.args.length == 0)) {
			String sender = info.getCommandSender().getName();
			String playa = info.args[0];
			Granite.getServer().sendMessage(Trustworthy.Name + "Player '" + sender + "' has requested a ban lookup for player '" + playa + "'");
			info.getCommandSender()
					.sendMessage(
							Trustworthy.Name
									+ "Your request is being processed, please wait...");
			EventQueue.execute(new Runnable() {
				public void run() {

					int MCBANS = TrustworthyStats.getStats(playa, "mcbans");
					int MCBOUNCER = TrustworthyStats.getStats(playa,
							"mcbouncer");
					int MCBLOCKIT = TrustworthyStats.getStats(playa,
							"mcblockit");
					int MINEBANS = TrustworthyStats.getStats(playa, "minebans");
					int GLIZER = TrustworthyStats.getStats(playa, "glizer");

					if (!((MCBANS + MCBOUNCER + MCBLOCKIT + MINEBANS + GLIZER) == 0)) {
						info.getCommandSender()
								.sendMessage(
										Trustworthy.Name
												+ "§cPlayer '"
												+ playa
												+ "'"
												+ " has "
												+ String.valueOf(MCBANS
														+ MCBOUNCER + MCBLOCKIT
														+ MINEBANS + GLIZER)
												+ " bans on record.");
					} else {
						info.getCommandSender().sendMessage(
								Trustworthy.Name + "§aPlayer '" + playa
										+ "' has no bans on records.");
					}
				}
			});
		} else {
			info.getCommandSender().sendMessage(
					Trustworthy.Name + "Syntax: /Trustworthy [Playername]");
		}
	}

}
