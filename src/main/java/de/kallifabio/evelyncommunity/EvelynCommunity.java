package de.kallifabio.evelyncommunity;

import de.kallifabio.evelyncommunity.commands.*;
import de.kallifabio.evelyncommunity.listeners.ChatListener;
import de.kallifabio.evelyncommunity.listeners.IPCommandListener;
import de.kallifabio.evelyncommunity.listeners.PlayerListener;
import de.kallifabio.evelyncommunity.listeners.SleepListener;
import de.kallifabio.evelyncommunity.manager.ActionbarManager;
import de.kallifabio.evelyncommunity.manager.BossbarManager;
import de.kallifabio.evelyncommunity.utils.AutoMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EvelynCommunity extends JavaPlugin {

    private static final String prefix = "&dEvelynCommunity&9Server &8» &r";
    private static final String noPermission = "&cDu hast keine Rechte dafür";
    private static EvelynCommunity instance;
    private static BossbarManager bossbarManager;

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static EvelynCommunity getInstance() {
        return instance;
    }

    private void registerEvents() {
        PluginManager plugin = Bukkit.getPluginManager();

        plugin.registerEvents(new PlayerListener(), this);
        plugin.registerEvents(new ChatListener(), this);
        plugin.registerEvents(new IPCommandListener(), this);
        plugin.registerEvents(new SleepListener(), this);
    }

    private void registerCommands() {
        getCommand("broadcast").setExecutor(new broadcastCommand());
        getCommand("ping").setExecutor(new pingCommand());
        getCommand("ip").setExecutor(new IPCommandListener());
        getCommand("gm").setExecutor(new gamemodeCommand());
        getCommand("day").setExecutor(new dayCommand());
        getCommand("night").setExecutor(new nightCommand());
        getCommand("socialmedia").setExecutor(new socialmediaCommand());
    }

    private void updateBar() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, ActionbarManager::updateActionBar, 0L, 40L);
    }

    public static String getPrefix() {
        return prefix;
    }

    public static String getNoPermission() {
        return noPermission;
    }

    public static BossbarManager getBossbarManager() {
        return bossbarManager;
    }

    @Override
    public void onEnable() {
        instance = this;
        registerEvents();
        registerCommands();
        updateBar();
        bossbarManager = new BossbarManager();
        bossbarManager.createBar();
        if (Bukkit.getOnlinePlayers().size() > 0) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                bossbarManager.addPlayer(online);
            }
        }
        new AutoMessage().startAutoMessage();
        new IPCommandListener().getBuilder().save();
        Bukkit.getConsoleSender().sendMessage(format(prefix + "&2Das Plugin wurde mit Version &e" + getInstance().getDescription().getVersion() + " &2gestartet"));
    }

    @Override
    public void onDisable() {
        new IPCommandListener().getBuilder().save();
        Bukkit.getConsoleSender().sendMessage(format(prefix + "&4Das Plugin wurde mit Version &e" + getInstance().getDescription().getVersion() + " &4gestoppt"));
    }
}
