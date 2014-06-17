package com.goatgoose.bustaplugdj.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.glassfish.tyrus.server.Server;

public class StartSocketServerTask extends BukkitRunnable {

    Server server;

    public StartSocketServerTask(Server server) {
        this.server = server;
    }

    public void run() {
        try {
            server.start();
        } catch(Exception e) {
            Bukkit.broadcastMessage("Unable to launch socket server:");
            e.printStackTrace();
        }
    }

}
