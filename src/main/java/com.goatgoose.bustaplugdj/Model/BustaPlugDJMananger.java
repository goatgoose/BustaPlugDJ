package com.goatgoose.bustaplugdj.model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.plugdj.EventListener;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BustaPlugDJMananger {

    private static BustaPlugDJMananger INSTANCE = null;

    private BustaPlugDJ plugin;

    private EventListener eventListener;

    private HashMap<String, BustaPlayer> bustaPlayers = new HashMap<String, BustaPlayer>();

    private Stage stage;

    public BustaPlugDJMananger(BustaPlugDJ plugin) {
        if(INSTANCE == null) {
            INSTANCE = this;
            this.plugin = plugin;
            this.eventListener = new EventListener(INSTANCE);
            this.stage = new Stage(plugin);
        }
    }

    public static BustaPlugDJMananger getInstance() {
        return INSTANCE;
    }

    public void addPlugDJPlayer(BustaPlayer bustaPlayer) {
        bustaPlayers.put(bustaPlayer.getPlayer().getName(), bustaPlayer);
    }

    public void removePlugDJPlayer(BustaPlayer bustaPlayer) {
        bustaPlayers.remove(bustaPlayer.getPlayer().getName());
    }

    public BustaPlayer getPlugDJPlayer(Player player) {
        return bustaPlayers.get(player.getName());
    }

    public Stage getStage() {
        return stage;
    }

    public BustaPlugDJ getPlugin() {
        return plugin;
    }

    public EventListener getEventListener() {
        return eventListener;
    }

}
