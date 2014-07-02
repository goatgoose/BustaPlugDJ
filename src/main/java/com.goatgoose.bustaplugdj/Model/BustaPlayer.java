package com.goatgoose.bustaplugdj.model;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BustaPlayer {

    BustaPlugDJMananger manager = BustaPlugDJMananger.getInstance();

    public enum Status {
        DJ,
        AUDIENCE,
        SETUP_FIREWORKS,
        SETUP_FIRE,
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
            player.sendMessage(manager.prefix + "You are now DJ!");
            BustaPlayer currentDJ = manager.getCurrentDJ();
            if(currentDJ != null) {
                currentDJ.setStatus(Status.AUDIENCE);
            }
            Location djSpawn = manager.getDjSpawn();
            if(djSpawn != null) {
                player.teleport(djSpawn);
            }

            Inventory inventory = getPlayer().getInventory();
            inventory.clear();
            inventory.addItem(new ItemStack(Material.GLOWSTONE));
            inventory.addItem(new ItemStack(Material.FIREWORK));
            inventory.addItem(new ItemStack(Material.FIRE));
        }

        if(status == Status.AUDIENCE) {
            getPlayer().getInventory().clear();
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
