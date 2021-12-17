package de.kallifabio.evelyncommunity.listeners;

import de.kallifabio.evelyncommunity.EvelynCommunity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class PlayerListener implements Listener {

    Player player;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        player = event.getPlayer();
        event.setJoinMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&7" + player.getName() + " &ahat den Server betreten"));
        player.sendTitle(EvelynCommunity.format("&8Willkommen &7" + player.getName()), EvelynCommunity.format(" &8auf dem &dEvelynCommunity&9Server"), 25, 35, 25);
        resetCooldown();
        new IPCommandListener().getBuilder().save();
        if (!EvelynCommunity.getBossbarManager().getBar().getPlayers().contains(player)) {
            EvelynCommunity.getBossbarManager().addPlayer(player);
        }
        setAllPlayerTeams();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        player = event.getPlayer();
        event.setQuitMessage(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&7" + player.getName() + " &chat den Server verlassen"));
        new IPCommandListener().getBuilder().save();
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        player = event.getPlayer();
        event.setReason(EvelynCommunity.format(EvelynCommunity.getPrefix() + "&7Du wurdest gekickt."));
        new IPCommandListener().getBuilder().save();
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        player = event.getPlayer();
        new BukkitRunnable() {
            @Override
            public void run() {
                resetCooldown();
            }
        }.runTaskLater(EvelynCommunity.getInstance(), 20);
    }

    private void resetCooldown() {
        player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100D);
    }

    private void setAllPlayerTeams() {
        Bukkit.getOnlinePlayers().forEach(this::setPlayerTeams);
    }

    private void setPlayerTeams(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Team owner = scoreboard.getTeam("001");
        Team sub = scoreboard.getTeam("002");
        Team players = scoreboard.getTeam("003");

        if (owner == null) {
            owner = scoreboard.registerNewTeam("001");
        }

        if (sub == null) {
            sub = scoreboard.registerNewTeam("002");
        }

        if (players == null) {
            players = scoreboard.registerNewTeam("003");
        }

        owner.setPrefix(EvelynCommunity.format("&4Owner &7| "));
        sub.setPrefix(EvelynCommunity.format("&3Sub &7| "));

        owner.setColor(ChatColor.DARK_RED);
        sub.setColor(ChatColor.DARK_AQUA);
        players.setColor(ChatColor.DARK_GRAY);

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target.getName().equals("kallifabio")) {
                owner.addEntry(target.getName());
                continue;
            }

            if (target.getName().equals("")) {
                sub.addEntry(target.getName());
                continue;
            }

            players.addEntry(target.getName());
        }

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(scoreboard);
        }
    }
}
