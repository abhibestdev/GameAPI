package me.abhi.gameapi.interfaces;

import org.bukkit.entity.Player;

public interface GameListener {

    void onPlayerJoinGame(Player player);

    void onPlayerQuitGame(Player player);

    void onPlayerDeath(Player player);

    void onPlayerLose(Player player);

    void onPlayerWinGame(Player player);

    void onGameStart();

    void onGameEnd();

    void onGameCancel();
}
