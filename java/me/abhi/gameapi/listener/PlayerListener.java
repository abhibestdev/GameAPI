package me.abhi.gameapi.listener;

import me.abhi.gameapi.GameAPI;
import me.abhi.gameapi.enums.PlayerState;
import me.abhi.gameapi.interfaces.GameListener;
import me.abhi.gameapi.player.GamePlayer;
import me.abhi.gameapi.util.GameSettings;
import me.abhi.gameapi.util.ScoreHelper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!GameSettings.isDefaultJoinMessageEnabled()) {
            event.setJoinMessage(null);
        }
        if (GameSettings.isBuiltInScoreboardEnabled()) {
            ScoreHelper.createScore(player);
        }
        if (!GameAPI.hasGamePlayer(player)) {
            GameAPI.addGamePlayer(player);
        }
        for (GameListener gameListener : GameAPI.getGameListeners()) {
            gameListener.onPlayerJoinGame(player);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!GameSettings.isDefaultQuitMessageEnabled()) {
            event.setQuitMessage(null);
        }
        if (ScoreHelper.hasScore(player)) {
            ScoreHelper.removeScore(player);
        }
        for (GameListener gameListener : GameAPI.getGameListeners()) {
            gameListener.onPlayerQuitGame(player);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        GamePlayer gamePlayer = GameAPI.getGamePlayer(player);
        if (GameSettings.isTrackDeaths()) {
            gamePlayer.setDeaths(gamePlayer.getDeaths() + 1);
        }
        if (GameSettings.isTrackKills()) {
            if (player.getKiller() != null && player.getKiller() instanceof Player) {
                GamePlayer gameKiller = GameAPI.getGamePlayer(player.getKiller());
                gameKiller.setKills(gameKiller.getKills() + 1);
            }
        }
        for (GameListener gameListener : GameAPI.getGameListeners()) {
            gameListener.onPlayerDeath(player);
        }
    }

    @EventHandler
    public void onDisable(PluginDisableEvent event) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
            player.kickPlayer(ChatColor.RED + "The server is restarting.");
        }
    }
}
