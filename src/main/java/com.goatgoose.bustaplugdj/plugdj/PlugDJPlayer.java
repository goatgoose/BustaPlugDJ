package com.goatgoose.bustaplugdj.plugdj;

import org.bukkit.entity.Player;

public class PlugDJPlayer {

    public enum Status {
        DJ,
        AUDIENCE,
        SETUP_FIREWORKS,
        ABSENT
    }

    private Status status = Status.ABSENT;

    private Player player;

    public PlugDJPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}