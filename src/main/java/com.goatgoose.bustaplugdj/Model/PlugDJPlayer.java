package com.goatgoose.bustaplugdj.Model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import org.bukkit.entity.Player;

public class PlugDJPlayer {

    private BustaPlugDJ plugin;

    private Player player;

    private boolean isDJ = false;

    private Stage stage;

    public PlugDJPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

}
