package com.goatgoose.bustaplugdj.EventHandlers;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.Model.PlugDJPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private BustaPlugDJ plugin;

    public PlayerListener(BustaPlugDJ instance) {
        this.plugin = instance;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        plugin.addPlugDJPlayer(new PlugDJPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        plugin.removePlugDJPlayer(new PlugDJPlayer(event.getPlayer()));
    }

}
