package me.abhi.gameapi.player;

import lombok.Getter;
import lombok.Setter;
import me.abhi.gameapi.enums.PlayerState;

@Getter
@Setter
public class GamePlayer {

    private int kills;
    private int deaths;
    private PlayerState playerState = PlayerState.PLAYING;

}
