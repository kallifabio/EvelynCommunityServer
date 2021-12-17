package de.kallifabio.evelynxkacommunity.commands;

import de.kallifabio.evelynxkacommunity.EvelynxkaCommunity;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class helpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "Du musst ein Spieler sein"));
            return true;
        }

        final Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("help")) {
            if (player.getName().equals("kallifabio")) {
                if (args.length == 0) {
                    player.sendMessage(EvelynxkaCommunity.format("&8---------&cAdmin-Help&8---------"));
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/broadcast &8- &7Sende einen Rundruf"));
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/gm &8- &7Der GameMode Befehl"));
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/ip &8- &7Sehe die IP von Spielern"));
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/ping &8- &7Sehe den Ping von Spielern"));
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/day &8- &7Setze die Zeit zu Tag"));
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/night &8- &7Setze die Zeit zu Nacht"));
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/socialmedia &8- &7Sehe das ganze Social Media von Evelyn"));
                    player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/help &8- &7Zeigt diese Hilfe hier"));
                    player.sendMessage(EvelynxkaCommunity.format("&8---------&cAdmin-Help&8---------"));
                }
            } else {
                player.sendMessage(EvelynxkaCommunity.format("&8---------&aSpieler-Help&8---------"));
                player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/ping &8- &7Sehe den Ping von Spielern"));
                player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/socialmedia &8- &7Sehe das ganze Social Media von Evelyn"));
                player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e/help &8- &7Zeigt diese Hilfe hier"));
                player.sendMessage(EvelynxkaCommunity.format("&8---------&aSpieler-Help&8---------"));
            }

            if (args.length >= 1) {
                player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&cBitte benutze &6/help"));
            }
        }
        return false;
    }
}
