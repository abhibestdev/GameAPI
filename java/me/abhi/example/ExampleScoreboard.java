package me.abhi.example;

import me.abhi.gameapi.GameAPI;
import me.abhi.gameapi.enums.PlayerState;
import me.abhi.gameapi.interfaces.GameScoreboard;
import me.abhi.gameapi.player.GamePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ExampleScoreboard implements GameScoreboard {

    public String getTitle() {
        return "&6&lExample Game";
    }

    public List<String> toReturn(Player player) {
        List<String> toReturn = new ArrayList();
        if (!GameAPI.hasGamePlayer(player)) {
            return null;
        }
        GamePlayer gamePlayer = GameAPI.getGamePlayer(player);
        toReturn.add("&7&m---------------------");
        switch (GameAPI.getGameState()) {
            case LOBBY: {
                toReturn.add("&7&oWaiting for players....");
                break;
            }
            case STARTING: {
                toReturn.add("&7&oThe game is starting!");
                break;
            }
            case STARTED: {
                toReturn.add("&7&oThe game has started!");
                break;
            }
            case ENDING: {
                toReturn.add("&7The game is ending!");
                break;
            }
            case ENDED: {
                toReturn.add("&7The game has ended!");
            }
        }
        toReturn.add("");
        toReturn.add("&6Kills: &f" + gamePlayer.getKills());
        toReturn.add("&6Deaths: &f" + gamePlayer.getDeaths());
        toReturn.add("&6Team: &f" + (gamePlayer.getPlayerState() == PlayerState.PLAYING ? "Players" : "Spectators"));
        toReturn.add("");
        toReturn.add("&7www.blockgame.us");
        toReturn.add("&7&m---------------------");
        return toReturn;
    }
}
