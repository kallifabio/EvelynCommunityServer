package de.kallifabio.evelynxkacommunity;

import de.kallifabio.evelynxkacommunity.commands.*;
import de.kallifabio.evelynxkacommunity.listeners.ChatListener;
import de.kallifabio.evelynxkacommunity.listeners.IPCommandListener;
import de.kallifabio.evelynxkacommunity.listeners.PlayerListener;
import de.kallifabio.evelynxkacommunity.listeners.SleepListener;
import de.kallifabio.evelynxkacommunity.manager.ActionbarManager;
import de.kallifabio.evelynxkacommunity.manager.BossbarManager;
import de.kallifabio.evelynxkacommunity.utils.AutoMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EvelynxkaCommunity extends JavaPlugin {

    private static final String prefix = "&dEvelynxkaCommunity&9Server &8» &r";
    private static final String noPermission = "&cDu hast keine Rechte dafür";
    private static EvelynxkaCommunity instance;
    private static BossbarManager bossbarManager;

    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static EvelynxkaCommunity getInstance() {
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
        getCommand("help").setExecutor(new helpCommand());
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
