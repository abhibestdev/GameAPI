package me.abhi.example;

import me.abhi.gameapi.GameAPI;
import me.abhi.gameapi.enums.GameState;
import me.abhi.gameapi.enums.PlayerState;
import me.abhi.gameapi.interfaces.GameListener;
import me.abhi.gameapi.player.GamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ExampleListener implements GameListener {

    public void onPlayerJoinGame(Player player) { // Do whatever you need when a player joins
        GamePlayer gamePlayer = GameAPI.getGamePlayer(player);
        if (GameAPI.getGameState() == GameState.LOBBY) {
            if (gamePlayer.getPlayerState() == PlayerState.PLAYING) {
                Bukkit.broadcastMessage(ChatColor.GREEN + player.getName() + " has joined the game!");
                if (Bukkit.getOnlinePlayers().size() < 12) {
                    Bukkit.broadcastMessage(ChatColor.YELLOW + "There are still " + (12 - Bukkit.getOnlinePlayers().size()) + " players needed to start the game!");
                    return;
                }
                GameAPI.callGameStart();
                return;
            }
        }
        if (GameAPI.getGameState() != GameState.LOBBY) {
            gamePlayer.setPlayerState(PlayerState.SPECTATING);
        }
    }

    public void onPlayerQuitGame(Player player) { // Do whatever you need when a player quits
        Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " has left the game!");
        if (GameAPI.getGameState() == GameState.LOBBY) {
            if (Bukkit.getOnlinePlayers().size() < 12) {
                Bukkit.broadcastMessage(ChatColor.YELLOW + "There are now " + (12 - Bukkit.getOnlinePlayers().size()) + " players needed to start the game!");
                return;
            }
        }
    }

    public void onPlayerDeath(Player player) { // Do whatever you need when a player dies
        GameAPI.callPlayerLose(player);
    }

    public void onPlayerWinGame(Player player) { // Do whatever you need for the winner
        Bukkit.broadcastMessage(ChatColor.GOLD + player.getName() + " has won the game!");
    }

    public void onPlayerLose(Player player) { // Do whatever you need when a player loses
        Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " has lost!");
    }

    public void onGameStart() { // Do whatever you need to start the game
        GameAPI.setGameState(GameState.STARTING);
        Bukkit.broadcastMessage(ChatColor.YELLOW + "The game has been started!");
        GameAPI.setGameState(GameState.STARTED);
    }

    public void onGameEnd() { // Do whatever you need to end the game
        GameAPI.setGameState(GameState.ENDING);
        Bukkit.broadcastMessage(ChatColor.YELLOW + "The game has ended!");
        for (Player player : Bukkit.getOnlinePlayers()) {
            GameAPI.resetGamePlayer(player);
        }
        GameAPI.setGameState(GameState.ENDED);
    }

    public void onGameCancel() { // Do whatever you need to cancel the game
        GameAPI.setGameState(GameState.ENDING);
        Bukkit.broadcastMessage(ChatColor.YELLOW + "The game has been cancelled!");
        GameAPI.setGameState(GameState.ENDED);
    }
}

