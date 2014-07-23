package com.goatgoose.bustaplugdj.model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.tasks.LaunchFireTask;
import com.goatgoose.bustaplugdj.tasks.LaunchFireworkTask;
import com.goatgoose.bustaplugdj.tasks.TurnOffRedstoneBlocksTask;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;

public class Stage {

    private BustaPlugDJ plugin;

    private ArrayList<Block> displayBlocks = new ArrayList<Block>();

    private ArrayList<FireworkLauncher> fireworkLaunchers = new ArrayList<FireworkLauncher>();

    private ArrayList<FireLauncher> fireLaunchers = new ArrayList<FireLauncher>();

    private BukkitTask launchFireTask = null;

    public Stage(BustaPlugDJ instance) {
        this.plugin = instance;
    }

    public void flashDisplay() {
        for(Block block : displayBlocks) {
            block.setType(Material.REDSTONE_LAMP_ON);
        }
        new TurnOffRedstoneBlocksTask(displayBlocks).runTaskLater(plugin, 5);
    }

    public void launchFireworks() {
        for(FireworkLauncher fireworkLauncher : fireworkLaunchers) {
            fireworkLauncher.launchFirework();
        }
    }

    public void toggleFire() {
        if(launchFireTask == null) {
            launchFireTask = new LaunchFireTask(fireLaunchers).runTaskTimer(plugin, 0, 4);
        } else {
            launchFireTask.cancel();
            launchFireTask = null;
        }
    }

    public void addDisplayBlock(Block block) {
        displayBlocks.add(block);
    }

    public void addFireworkLauncher(FireworkLauncher fireworkLauncher) {
        if(!(fireworkLaunchers.contains(fireworkLauncher))) {
            fireworkLaunchers.add(fireworkLauncher);
        }
    }

    public void removeFireworkLauncher(Block block) {
        for(FireworkLauncher fireworkLauncher : fireworkLaunchers) {
            if(fireworkLauncher.getBlock() == block) {
                fireworkLaunchers.remove(fireworkLauncher);
                return;
            }
        }
    }

    public void addFireLauncher(FireLauncher fireLauncher) {
        if(!(fireLaunchers.contains(fireLauncher))) {
            fireLaunchers.add(fireLauncher);
        }
    }

    public void removeFireLauncher(Block block) {
        for(FireLauncher fireLauncher : fireLaunchers) {
            if(fireLauncher.getBlock() == block) {
                fireworkLaunchers.remove(fireLauncher);
                return;
            }
        }
    }
}
