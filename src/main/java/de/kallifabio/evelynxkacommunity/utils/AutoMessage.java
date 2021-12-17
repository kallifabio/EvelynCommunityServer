package de.kallifabio.evelynxkacommunity.utils;

import de.kallifabio.evelynxkacommunity.EvelynxkaCommunity;
import org.bukkit.Bukkit;

public class AutoMessage {

    public void startAutoMessage() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(EvelynxkaCommunity.getInstance(), () -> {
            Bukkit.broadcastMessage(EvelynxkaCommunity.format(" "));
            Bukkit.broadcastMessage(EvelynxkaCommunity.format("&8----------------------------"));
            Bukkit.broadcastMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&eEvelynxka Twitch Community Server"));
            Bukkit.broadcastMessage(EvelynxkaCommunity.format(EvelynxkaCommunity.getPrefix() + "&3Powered by §7kallifabio, Bero-Host"));
            Bukkit.broadcastMessage(EvelynxkaCommunity.format("&8----------------------------"));
            Bukkit.broadcastMessage(EvelynxkaCommunity.format(" "));
        }, 0, 18000); //alle 15 minuten
    }
}
