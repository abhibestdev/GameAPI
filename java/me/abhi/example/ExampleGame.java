package me.abhi.example;

import me.abhi.gameapi.GameAPI;
import me.abhi.gameapi.util.GameSettings;
import me.abhi.gameapi.enums.GameState;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleGame extends JavaPlugin {

    public void onEnable() {
        GameAPI.setJavaPlugin(this);
        GameAPI.addGameListener(new ExampleListener());
        GameAPI.setGameState(GameState.LOBBY);
        GameSettings.setDefaultJoinMessageEnabled(false);
        GameSettings.setDefaultQuitMessageEnabled(false);
        GameSettings.setBuiltInScoreboardEnabled(true);
        GameSettings.setTrackKills(true); // Usually would set to false if you want to track kills on your own. Ex: Mob kills
        GameSettings.setTrackDeaths(true); // Usually would set to false if you want to track deaths on your own. Similar to track kills set to false.
        GameAPI.setGameScoreboard(new ExampleScoreboard());
        GameAPI.registerCommand(new ExampleCommand());
        GameAPI.hook();
    }

}
