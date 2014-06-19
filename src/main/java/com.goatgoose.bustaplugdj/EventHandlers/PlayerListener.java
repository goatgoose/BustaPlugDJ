package com.goatgoose.bustaplugdj.eventHandlers;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.model.BustaPlugDJMananger;
import com.goatgoose.bustaplugdj.model.FireworkLauncher;
import com.goatgoose.bustaplugdj.model.BustaPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    private BustaPlugDJ plugin;

    private BustaPlugDJMananger manager = BustaPlugDJMananger.getInstance();

    public PlayerListener(BustaPlugDJ instance) {
        this.plugin = instance;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        manager.addBustaPlayer(new BustaPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        manager.removeBustaPlayer(new BustaPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        BustaPlayer bustaPlayer = manager.getBustaPlayer(event.getPlayer());

        if(bustaPlayer.getStatus() == BustaPlayer.Status.DJ) {

            if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                if(event.getPlayer().getInventory().getHeldItemSlot() == 0) {
                    event.setCancelled(true);
                    manager.getStage().flashDisplay();
                } else if(event.getPlayer().getInventory().getHeldItemSlot() == 1) {
                    event.setCancelled(true);
                    manager.getStage().flashDisplay();
                    manager.getStage().launchFireworks();
                }
            }

        } else if(bustaPlayer.getStatus() == BustaPlayer.Status.AUDIENCE) {
            event.setCancelled(true);
        } else if(bustaPlayer.getStatus() == BustaPlayer.Status.SETUP_FIREWORKS) {

            if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
                event.setCancelled(true);
                manager.getStage().addFireworkLauncher(new FireworkLauncher(plugin, event.getClickedBlock()));
                bustaPlayer.getPlayer().sendMessage("Block added to firework launchers");
            }

        }
    }

}
