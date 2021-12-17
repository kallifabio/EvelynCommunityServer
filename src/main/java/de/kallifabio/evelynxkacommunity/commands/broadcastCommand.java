package de.kallifabio.evelynxkacommunity.commands;

import de.kallifabio.evelynxkacommunity.EvelynxkaCommunity;
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
            sender.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "Du musst ein Spieler sein"));
            return true;
        }

        final Player player = (Player) sender;
        if (player.getName().equals("kallifabio")) {
            if (cmd.getName().equalsIgnoreCase("broadcast")) {
                if (args.length == 0) {
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&cBitte benutze &6/broadcast <Nachricht>"));
                }

                if (args.length >= 1) {
                    String message = "";
                    for (int i = 0; i < args.length; i++) {
                        message = message + args[i] + " ";
                    }

                    Bukkit.broadcastMessage(EvelynxkaCommunity.format(" "));
                    Bukkit.broadcastMessage(EvelynxkaCommunity.format("&4-----------&cBroadcast&4-----------"));
                    Bukkit.broadcastMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + message.replaceAll("&", "§")));
                    Bukkit.broadcastMessage(EvelynxkaCommunity.format("&4-----------&cBroadcast&4-----------"));
                    Bukkit.broadcastMessage(EvelynxkaCommunity.format(" "));

                    Bukkit.getOnlinePlayers().forEach(all -> all.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2));
                }
            }
        } else {
            player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + EvelynxkaCommunity.getNoPermission()));
        }
        return false;
    }
}
