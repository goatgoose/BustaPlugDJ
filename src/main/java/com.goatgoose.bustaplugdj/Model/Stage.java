package com.goatgoose.bustaplugdj.Model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.Tasks.TurnOffRedstoneBlocksTask;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashMap;

public class Stage {

    private BustaPlugDJ plugin;

    private ArrayList<Block> displayBlocks = new ArrayList<Block>();

    private ArrayList<FireworkLauncher> fireworkLaunchers = new ArrayList<FireworkLauncher>();

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

    public void addDisplayBlock(Block block) {
        displayBlocks.add(block);
    }

    public void addFireworkLauncher(FireworkLauncher fireworkLauncher) {
        if(!(fireworkLaunchers.contains(fireworkLauncher))) {
            fireworkLaunchers.add(fireworkLauncher);
        }
    }
}
