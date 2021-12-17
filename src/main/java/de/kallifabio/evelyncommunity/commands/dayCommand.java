package de.kallifabio.evelyncommunity.commands;

import de.kallifabio.evelyncommunity.EvelynCommunity;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class dayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "Du musst ein Spieler sein"));
            return true;
        }

        final Player player = (Player) sender;
        if (player.getName().equals("kallifabio")) {
            if (cmd.getName().equalsIgnoreCase("day")) {
                if (args.length == 0) {
                    Bukkit.getWorld("world").setTime(1000L);
                    player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&2Du hast die Zeit zu Tag geändert"));
                }

                if (args.length > 0) {
                    player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&cBitte benutze &6/day"));
                }
            }
        } else {
            player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + EvelynCommunity.getNoPermission()));
        }
        return false;
    }
}
