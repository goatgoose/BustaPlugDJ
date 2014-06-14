package com.goatgoose.bustaplugdj.Model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class FireworkLauncher {

    private BustaPlugDJ plugin;

    private Block block;

    public FireworkLauncher(BustaPlugDJ instance, Block block) {
        this.plugin = instance;
        this.block = block;
    }

    public void launchFirework() {
        block.getWorld().spawnEntity(block.getLocation(), EntityType.FIREWORK);
    }

    public void launchFirework(long delay) { // in ticks
        if(delay <= 0) {
            launchFirework();
        } else {
            new LaunchFireworkTask(this).runTaskLater(plugin, delay);
        }
    }

}
