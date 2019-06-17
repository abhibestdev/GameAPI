package me.abhi.gameapi.interfaces;

import org.bukkit.entity.Player;

import java.util.List;

public interface GameScoreboard {

    String getTitle();

    List<String> toReturn(Player player);
}
