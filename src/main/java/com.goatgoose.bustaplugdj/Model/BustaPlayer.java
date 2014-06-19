package com.goatgoose.bustaplugdj.model;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class BustaPlayer {

    BustaPlugDJMananger manager = BustaPlugDJMananger.getInstance();

    public enum Status {
        DJ,
        AUDIENCE,
        SETUP_FIREWORKS,
        ABSENT
    }

    private Status status = Status.ABSENT;

    private Player player;

    private String plugDJUsername;

    public BustaPlayer(Player player) {
        this.player = player;
        this.plugDJUsername = player.getName();
    }

    public Player getPlayer() {
        return player;
    }

    public void setStatus(Status status) {

        if(status == Status.DJ) {
            BustaPlayer currentDJ = manager.getCurrentDJ();
            if(currentDJ != null) {
                currentDJ.setStatus(Status.AUDIENCE);
            }
            Location djSpawn = manager.getDjSpawn();
            if(djSpawn != null) {
                player.teleport(djSpawn);
            }
        }

        if(status == Status.AUDIENCE) {
            Location audienceSpawn = manager.getAudienceSpawn();
            if(audienceSpawn != null) {
                player.teleport(audienceSpawn);
            }
        }

        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setPlugDJUsername(String plugDJUsername) {
        this.plugDJUsername = plugDJUsername;
    }

    public String getPlugDJUsername() {
        return plugDJUsername;
    }

}
