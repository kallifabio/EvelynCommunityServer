package de.kallifabio.evelyncommunity.commands;

import de.kallifabio.evelyncommunity.EvelynCommunity;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class pingCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "Du musst ein Spieler sein"));
            return true;
        }

        final Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("ping")) {
            if (args.length == 0) {
                player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&6Du hast einen Ping von &9" + getPing(player) + "ms"));
            }

            if (args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&cDer Spieler &7" + args[0] + " &cwurde nicht gefunden"));
                } else {
                    player.sendMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&9" + Bukkit.getServer().getPlayer(args[0]).getName().toString() + " &6hat einen Ping von &9" + getPing(target) + "ms"));
                }
            }
        }
        return false;
    }

    public int getPing(Player player) {
        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        if (!player.getClass().getName().equals("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer")) { //compatibility with some plugins
            Bukkit.getPlayer(player.getUniqueId()); //cast to org.bukkit.entity.Player
        }
        try {
            Class<?> CraftPlayerClass = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
            Object CraftPlayer = CraftPlayerClass.cast((Player) player);
            Method getHandle = CraftPlayer.getClass().getMethod("getHandle");
            Object EntityPlayer = getHandle.invoke(CraftPlayer);
            Field ping = EntityPlayer.getClass().getDeclaredField("ping");
            ping.setAccessible(true);
            return ping.getInt(EntityPlayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
