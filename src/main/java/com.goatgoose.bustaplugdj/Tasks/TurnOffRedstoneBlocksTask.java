package com.goatgoose.bustaplugdj.Tasks;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class TurnOffRedstoneBlocksTask extends BukkitRunnable {

    private ArrayList<Block> redstoneLampBlocks;

    public TurnOffRedstoneBlocksTask(ArrayList<Block> redstoneLampBlocks) {
        this.redstoneLampBlocks = redstoneLampBlocks;
    }

    public void run() {
        for(Block block : redstoneLampBlocks) {
            block.setType(Material.REDSTONE_LAMP_OFF);
        }
    }

}
