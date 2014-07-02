package com.goatgoose.bustaplugdj.model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.plugdj.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class BustaPlugDJMananger {

    private static BustaPlugDJMananger INSTANCE = null;

    private BustaPlugDJ plugin;

    private EventListener eventListener;

    private HashMap<String, BustaPlayer> bustaPlayers = new HashMap<String, BustaPlayer>();

    private Stage stage;

    private Location djSpawn;

    private Location audienceSpawn;

    public final String prefix = ChatColor.GRAY + "[" + ChatColor.BLUE + "BustaPlugDJ" + ChatColor.GRAY + "] " + ChatColor.LIGHT_PURPLE;

    public BustaPlugDJMananger(BustaPlugDJ plugin) {
        if(INSTANCE == null) {
            INSTANCE = this;
            this.plugin = plugin;
            this.eventListener = new EventListener(INSTANCE);
            this.stage = new Stage(plugin);
        }
    }

    public BustaPlayer getCurrentDJ() {
        for(String key : bustaPlayers.keySet()) {
            BustaPlayer bustaPlayer = bustaPlayers.get(key);
            if(bustaPlayer.getStatus() == BustaPlayer.Status.DJ) {
                return bustaPlayer;
            }
        }
        return null;
    }

    public static BustaPlugDJMananger getInstance() {
        return INSTANCE;
    }

    public void addBustaPlayer(BustaPlayer bustaPlayer) {
        bustaPlayers.put(bustaPlayer.getPlayer().getName(), bustaPlayer);
    }

    public void removeBustaPlayer(BustaPlayer bustaPlayer) {
        bustaPlayers.remove(bustaPlayer.getPlayer().getName());
    }

    public BustaPlayer getBustaPlayer(Player player) {
        if(bustaPlayers.get(player.getName()) == null) {
            BustaPlayer bustaPlayer = new BustaPlayer(player);
            addBustaPlayer(bustaPlayer);
            return bustaPlayer;
        } else {
            return bustaPlayers.get(player.getName());
        }
    }

    public BustaPlayer getBustaPlayer(String plugDJUsername) {
        for(String key : bustaPlayers.keySet()) {
            BustaPlayer bustaPlayer = bustaPlayers.get(key);
            if(bustaPlayer.getPlugDJUsername().equalsIgnoreCase(plugDJUsername)) {
                return bustaPlayer;
            }
        }
        return null;
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

    public Location getDjSpawn() {
        return djSpawn;
    }

    public void setDjSpawn(Location djSpawn) {
        this.djSpawn = djSpawn;
    }

    public Location getAudienceSpawn() {
        return audienceSpawn;
    }

    public void setAudienceSpawn(Location audienceSpawn) {
        this.audienceSpawn = audienceSpawn;
    }

}
