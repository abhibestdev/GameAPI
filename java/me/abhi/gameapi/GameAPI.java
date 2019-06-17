package me.abhi.gameapi;

import lombok.Getter;
import lombok.Setter;
import me.abhi.gameapi.command.CommandHandler;
import me.abhi.gameapi.enums.GameState;
import me.abhi.gameapi.interfaces.GameListener;
import me.abhi.gameapi.interfaces.GameScoreboard;
import me.abhi.gameapi.listener.PlayerListener;
import me.abhi.gameapi.player.GamePlayer;
import me.abhi.gameapi.util.ScoreboardUpdater;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class GameAPI {

    @Getter
    @Setter
    private static JavaPlugin javaPlugin;

    @Getter
    @Setter
    private static GameState gameState;

    @Getter
    private static List<GameListener> gameListeners = new ArrayList();

    private static Map<UUID, GamePlayer> gamePlayerMap = new HashMap();

    @Getter
    private static List<Object> commands = new ArrayList();

    @Getter
    @Setter
    private static GameScoreboard gameScoreboard;

    public static void addGameListener(GameListener gameListener) {
        gameListeners.add(gameListener);
    }

    public static void addGamePlayer(Player player) {
        gamePlayerMap.put(player.getUniqueId(), new GamePlayer());
    }

    public static void removeGamePlayer(Player player) {
        gamePlayerMap.remove(player.getUniqueId());
    }

    public static GamePlayer getGamePlayer(Player player) {
        return gamePlayerMap.get(player.getUniqueId());
    }

    public static boolean hasGamePlayer(Player player) {
        return gamePlayerMap.containsKey(player.getUniqueId());
    }

    public static void resetGamePlayer(Player player) {
        GamePlayer gamePlayer = getGamePlayer(player);
        gamePlayer.setKills(0);
        gamePlayer.setDeaths(0);
    }

    public static void registerCommand(Object object) {
        commands.add(object);
    }

    public static void hook() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), javaPlugin);
        new ScoreboardUpdater().runTaskTimerAsynchronously(javaPlugin, 0L, 0L);
        CommandHandler commandHandler = new CommandHandler(javaPlugin);
        for (Object object : commands) {
            commandHandler.register(object);
        }
    }

    public static void callPlayerWin(Player player) {
        for (GameListener gameListener : gameListeners) {
            gameListener.onPlayerWinGame(player);
        }
    }

    public static void callPlayerLose(Player player) {
        for (GameListener gameListener : gameListeners) {
            gameListener.onPlayerLose(player);
        }
    }

    public static void callGameStart() {
        for (GameListener gameListener : gameListeners) {
            gameListener.onGameStart();
        }
    }

    public static void callGameEnd() {
        for (GameListener gameListener : gameListeners) {
            gameListener.onGameEnd();
        }
    }

    public static void callGameCancel() {
        for (GameListener gameListener : gameListeners) {
            gameListener.onGameCancel();
        }
    }
}
