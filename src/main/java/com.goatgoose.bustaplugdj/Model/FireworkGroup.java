package com.goatgoose.bustaplugdj.Model;

import org.bukkit.block.Block;

import java.util.ArrayList;

public class FireworkGroup {

    private String name;

    private ArrayList<FireworkLauncher> fireworkLaunchers;

    private Block activateBlock;

    public FireworkGroup(String name) {
        this.name = name;
        this.fireworkLaunchers = new ArrayList<FireworkLauncher>();
    }

    public void launchFireworks() {
        long delay = 4; // ticks
        long i = 0; // ticks
        for(FireworkLauncher fireworkLauncher : fireworkLaunchers) {
            fireworkLauncher.launchFirework(i);
            i = i + delay;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFireworkLauncher(FireworkLauncher fireworkLauncher) {
        fireworkLaunchers.add(fireworkLauncher);
    }

    public void setActivateBlock(Block block) {
        this.activateBlock = block;
    }

    public Stage getStage() {
        return stage;
    }

}
