package de.kallifabio.evelyncommunity.commands;

import de.kallifabio.evelyncommunity.EvelynCommunity;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class broadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "Du musst ein Spieler sein"));
            return true;
        }

        final Player player = (Player) sender;
        if (player.getName().equals("kallifabio")) {
            if (cmd.getName().equalsIgnoreCase("broadcast")) {
                if (args.length == 0) {
                    player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&cBitte benutze &6/broadcast <Nachricht>"));
                }

                if (args.length >= 1) {
                    String message = "";
                    for (int i = 0; i < args.length; i++) {
                        message = message + args[i] + " ";
                    }

                    Bukkit.broadcastMessage(EvelynCommunity.format(" "));
                    Bukkit.broadcastMessage(EvelynCommunity.format("&4-----------&cBroadcast&4-----------"));
                    Bukkit.broadcastMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + message.replaceAll("&", "§")));
                    Bukkit.broadcastMessage(EvelynCommunity.format("&4-----------&cBroadcast&4-----------"));
                    Bukkit.broadcastMessage(EvelynCommunity.format(" "));

                    Bukkit.getOnlinePlayers().forEach(all -> all.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2));
                }
            }
        } else {
            player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + EvelynCommunity.getNoPermission()));
        }
        return false;
    }
}
