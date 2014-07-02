package com.goatgoose.bustaplugdj.tasks;

import com.goatgoose.bustaplugdj.model.FireLauncher;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class LaunchFireTask extends BukkitRunnable {

    private ArrayList<FireLauncher> fireLaunchers;

    public LaunchFireTask(ArrayList<FireLauncher> fireLaunchers) {
        this.fireLaunchers = fireLaunchers;
    }

    public void run() {
        for(FireLauncher fireLauncher : fireLaunchers) {
            fireLauncher.launchFire();
        }
    }
}
