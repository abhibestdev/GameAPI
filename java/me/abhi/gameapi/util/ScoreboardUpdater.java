package me.abhi.gameapi.util;

import me.abhi.gameapi.GameAPI;
import me.abhi.gameapi.interfaces.GameScoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ScoreboardUpdater extends BukkitRunnable {

    public void run() {
        try {
            if (!GameSettings.isBuiltInScoreboardEnabled()) {
                return;
            }
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!ScoreHelper.hasScore(player)) {
                    continue;
                }
                ScoreHelper scoreHelper = ScoreHelper.getByPlayer(player);
                GameScoreboard gameScoreboard = GameAPI.getGameScoreboard();
                scoreHelper.setTitle(gameScoreboard.getTitle());
                if (gameScoreboard.toReturn(player) == null) {
                    for (int i = 0; i <= 15; i++) {
                        scoreHelper.removeSlot(i);
                        continue;
                    }
                }
                scoreHelper.setSlotsFromList(gameScoreboard.toReturn(player));
                if (gameScoreboard.toReturn(player).size() < 15) {
                    for (int i = gameScoreboard.toReturn(player).size() + 1; i <= 15; i++) {
                        scoreHelper.removeSlot(i);
                    }
                }
            }
        } catch (Exception ex) {
            // Removes dumb errors
        }
    }
}
