package de.kallifabio.evelyncommunity.manager;

import de.kallifabio.evelyncommunity.EvelynCommunity;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class BossbarManager {

    private int taskID = -1;
    private BossBar bar;

    public void addPlayer(Player player) {
        bar.addPlayer(player);
    }

    public BossBar getBar() {
        return bar;
    }

    public void createBar() {
        bar = Bukkit.createBossBar(EvelynCommunity.format("&3Powered by §7kallifabio, Bero-Host"), BarColor.BLUE, BarStyle.SOLID);
        bar.setVisible(true);
        task();
    }

    public void task() {
        taskID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(EvelynCommunity.getInstance(), new Runnable() {
            int count = -1;
            double progress = 1.0;
            double time = 1.0 / 30;

            @Override
            public void run() {
                bar.setProgress(progress);
                switch (count) {
                    case -1:
                        break;
                    case 0:
                        bar.setColor(BarColor.GREEN);
                        bar.setStyle(BarStyle.SOLID);
                        bar.setTitle(EvelynCommunity.format("&3Instagram&8: &eevelynkafriedrich"));
                        break;
                    case 1:
                        bar.setColor(BarColor.PINK);
                        bar.setStyle(BarStyle.SOLID);
                        bar.setTitle(EvelynCommunity.format("&3Discord&8: &eIn Wartung..."));
                        break;
                    case 2:
                        bar.setColor(BarColor.YELLOW);
                        bar.setStyle(BarStyle.SOLID);
                        bar.setTitle(EvelynCommunity.format("&eEvelynxka Twitch Community Server"));
                        break;
                    default:
                        bar.setColor(BarColor.BLUE);
                        bar.setStyle(BarStyle.SOLID);
                        bar.setTitle(EvelynCommunity.format("&3Powered by §7kallifabio, Bero-Host"));
                        count = -1;
                        break;
                }
                progress = progress - time;
                if (progress <= 0) {
                    count++;
                    progress = 1.0;
                }
            }
        }, 0, 20);
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }
}
