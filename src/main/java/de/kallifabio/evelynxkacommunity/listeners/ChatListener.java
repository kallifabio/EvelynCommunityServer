package de.kallifabio.evelynxkacommunity.listeners;

import de.kallifabio.evelynxkacommunity.EvelynxkaCommunity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.List;

public class ChatListener implements Listener {

    Player player;

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        player = event.getPlayer();
        String message = event.getMessage().replace("%", "%%");
        String msg = event.getMessage();
        Player target = Bukkit.getServer().getPlayer(player.getName());

        String owner = EvelynxkaCommunity.format("&4Owner &7| &4" + player.getName() + "&7 » &e" + message);
        String sub = EvelynxkaCommunity.format("&3Sub &7| &3" + player.getName() + "&7 » &e" + message);
        String defaultplayer = EvelynxkaCommunity.format("&8" + player.getName() + "&7 » &r") + message;

        if (player.getName().equals("kallifabio")) {
            event.setFormat(owner);
        } else if (player.getName().equals("")) {
            event.setFormat(sub);
        } else {
            event.setFormat(defaultplayer);
        }

        /*if (message.contains(target.getName())) {
            event.setMessage(TwitterServer.format(message + " &9" + player.getName()));
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 2);
            target.playSound(target.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 2);
        }*/

        List<String> blockedWords = new ArrayList<>();
        blockedWords.add("penis");
        blockedWords.add("hure");
        blockedWords.add("hurensohn");
        blockedWords.add("scheisse");
        blockedWords.add("kacke");
        blockedWords.add("scheiße");
        blockedWords.add("dreck");
        blockedWords.add("ss");
        blockedWords.add("hitler");
        blockedWords.add("heil hitler");
        blockedWords.add("sieg heil");
        blockedWords.add("mistgeburt");
        blockedWords.add("schlampe");
        blockedWords.add("fotze");

        for (String blocked : blockedWords) {
            if (player.getName().equals("kallifabio")) {
                event.setCancelled(false);
            } else if (message.contains(blocked)) {
                event.setCancelled(true);
                player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e" + player.getName() + " &cAchte auf deine Wortwahl &8[&e" + blocked + "&8]"));
            }
        }

        List<String> blockedWerbung = new ArrayList<>();
        blockedWerbung.add(".de");
        blockedWerbung.add(".net");
        blockedWerbung.add(".me");
        blockedWerbung.add(".eu");
        blockedWerbung.add(".com");
        blockedWerbung.add(".io");
        blockedWerbung.add(".tv");
        blockedWerbung.add(".eu");
        blockedWerbung.add("gommehd");
        blockedWerbung.add("GommeHD");
        blockedWerbung.add("Kommt alle auf");

        for (String werbung : blockedWerbung) {
            if (player.getName().equals("kallifabio")) {
                event.setCancelled(false);
            } else if (message.contains(werbung)) {
                event.setCancelled(true);
                player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&e" + player.getName() + " &cDier ist es nicht erlaubt Werbung zu machen &8[&e" + werbung + "&8]"));
            }
        }
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        player = event.getPlayer();

        if (command.toLowerCase().startsWith("/me") || command.toLowerCase().startsWith("/?") || command.toLowerCase().startsWith("/about") ||
                command.toLowerCase().startsWith("/bukkit") || command.toLowerCase().startsWith("/version")) {
            if (player.getName().equals("kallifabio")) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
                player.sendMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + EvelynxkaCommunity.getNoPermission()));
            }
        }

        if (command.toLowerCase().startsWith("/pl") || command.toLowerCase().startsWith("/plugins")) {
            if (player.getName().equals("kallifabio")) {
                event.setCancelled(false);
            } else {
                event.setCancelled(true);
                player.sendMessage(EvelynxkaCommunity.format("&fPlugins (6): &aTwitter&f, &aHackertool by Hack4Crack&f, &aWorldedit&f, &eViaVersion&f, &aAAC&f, &cAuthMe"));
            }
        }
    }
}
