package de.kallifabio.evelynxkacommunity.commands;

import de.kallifabio.evelynxkacommunity.EvelynxkaCommunity;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class nightCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "Du musst ein Spieler sein"));
            return true;
        }

        final Player player = (Player) sender;
        if (player.getName().equals("kallifabio")) {
            if (cmd.getName().equalsIgnoreCase("night")) {
                if (args.length == 0) {
                    Bukkit.getWorld("world").setTime(13000L);
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&2Du hast die Zeit zu Nacht geändert"));
                }

                if (args.length > 0) {
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&cBitte benutze &6/night"));
                }
            }
        } else {
            player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + EvelynxkaCommunity.getNoPermission()));
        }
        return false;
    }
}
