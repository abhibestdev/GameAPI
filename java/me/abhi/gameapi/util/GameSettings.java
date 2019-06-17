package me.abhi.gameapi.util;


import lombok.Getter;
import lombok.Setter;

public class GameSettings {

    @Getter
    @Setter
    private static boolean defaultJoinMessageEnabled = true;

    @Getter
    @Setter
    private static boolean defaultQuitMessageEnabled = true;

    @Getter
    @Setter
    private static boolean builtInScoreboardEnabled = true;

    @Getter
    @Setter
    private static boolean trackKills = true;

    @Getter
    @Setter
    private static boolean trackDeaths = true;
}
