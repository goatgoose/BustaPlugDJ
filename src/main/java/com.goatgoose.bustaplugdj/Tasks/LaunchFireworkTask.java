package com.goatgoose.bustaplugdj.Tasks;

import com.goatgoose.bustaplugdj.Model.FireworkLauncher;
import org.bukkit.scheduler.BukkitRunnable;

public class LaunchFireworkTask extends BukkitRunnable {

    private FireworkLauncher fireworkLauncher;

    public LaunchFireworkTask(FireworkLauncher fireworkLauncher) {
        this.fireworkLauncher = fireworkLauncher;
    }

    public void run() {
        fireworkLauncher.launchFirework();
    }

}
