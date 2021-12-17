package de.kallifabio.evelynxkacommunity.commands;

import de.kallifabio.evelynxkacommunity.EvelynxkaCommunity;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class socialmediaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "Du musst ein Spieler sein"));
            return true;
        }

        final Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("socialmedia")) {
            if (args.length == 0) {
                Bukkit.broadcastMessage(EvelynxkaCommunity.format(" "));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&8-----------&7SocialMedia&8-----------"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2Twitch&8: &ahttps://www.twitch.tv/evelynxka"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2Instagram&8: &ahttps://www.instagram.com/evelynkafriedrich/"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2YouTube&8: &ahttps://www.youtube.com/channel/UCH38RV8JOjfvs1HZfBQHczg"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2TikTok&8: &ahttps://www.tiktok.com/@evelynkafriedrich"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2Discord-Server&8: &cIn Wartung"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2Discord-Name&8: &aeveloveschicken#2019"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2Twitch-Sub&8: &ahttps://www.twitch.tv/products/evelynxka"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2Streamplan&8: &ahttps://www.twitch.tv/evelynxka/schedule"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2Wishlist&8: &ahttps://www.amazon.de/hz/wishlist/ls/1KK961180OQJ7?ref_=wl_share"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&2Donation&8: &ahttps://streamlabs.com/evelynxka/"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format("&8-----------&7SocialMedia&8-----------"));
                Bukkit.broadcastMessage(EvelynxkaCommunity.format(" "));
            }

            if (args.length >= 1) {
                player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&cBitte benutze &6/socialmedia"));
            }
        } else {
            player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&cBitte benutze &6/socialmedia"));
        }
        return false;
    }
}
