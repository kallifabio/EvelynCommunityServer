package de.kallifabio.evelyncommunity.listeners;

import de.kallifabio.evelyncommunity.EvelynCommunity;
import de.kallifabio.evelyncommunity.utils.FileBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class IPCommandListener implements Listener, CommandExecutor {

    FileBuilder builder = new FileBuilder("plugins/EvelynCommunity", "ip.yml");

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        String ip = event.getAddress().getHostAddress();

        builder.setValue("IPs." + player.getUniqueId() + ".IP", ip);
        builder.save();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "Du musst ein Spieler sein"));
            return true;
        }

        final Player player = (Player) sender;
        if (player.getName().equals("kallifabio")) {
            if (cmd.getName().equalsIgnoreCase("ip")) {
                if (args.length == 0) {
                    player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&cBitte benutze &6/ip <Name>"));
                }

                if (args.length == 1) {
                    String target = args[0];
                    if (builder.getString("IPs." + getUUID(target) + ".IP") != null) {
                        String ip = builder.getString("IPs." + getUUID(target) + ".IP");
                        player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&6Die IP von &9" + target + " &6ist &9" + ip));
                    } else {
                        player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&cDer Spieler wurde nicht gefunden"));
                    }
                }
            }
        } else {
            player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + EvelynCommunity.getNoPermission()));
        }
        return false;
    }

    private String getUUID(String name) {
        return Bukkit.getOfflinePlayer(name).getUniqueId().toString();
    }

    public FileBuilder getBuilder() {
        return builder;
    }
}
