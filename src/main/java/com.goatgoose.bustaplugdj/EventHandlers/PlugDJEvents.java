package com.goatgoose.bustaplugdj.EventHandlers;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import org.bukkit.Bukkit;

public class PlugDJEvents {

    BustaPlugDJ plugin;

    public PlugDJEvents(BustaPlugDJ instance) {
        this.plugin = instance;
    }

    public void onDJUpdate(String name) {
        Bukkit.broadcastMessage(name);
    }

}
