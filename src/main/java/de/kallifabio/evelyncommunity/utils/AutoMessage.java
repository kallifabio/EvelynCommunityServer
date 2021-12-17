package de.kallifabio.evelyncommunity.utils;

import de.kallifabio.evelyncommunity.EvelynCommunity;
import org.bukkit.Bukkit;

public class AutoMessage {

    public void startAutoMessage() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(EvelynCommunity.getInstance(), () -> {
            Bukkit.broadcastMessage(EvelynCommunity.format(" "));
            Bukkit.broadcastMessage(EvelynCommunity.format("&8----------------------------"));
            Bukkit.broadcastMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&eEvelynxka Twitch Community Server"));
            Bukkit.broadcastMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&3Powered by §7kallifabio, Bero-Host"));
            Bukkit.broadcastMessage(EvelynCommunity.format("&8----------------------------"));
            Bukkit.broadcastMessage(EvelynCommunity.format(" "));
        }, 0, 18000); //alle 15 minuten
    }
}
