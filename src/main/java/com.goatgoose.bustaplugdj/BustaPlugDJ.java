package com.goatgoose.bustaplugdj;

import com.goatgoose.bustaplugdj.EventHandlers.PlayerListener;
import com.goatgoose.bustaplugdj.EventHandlers.PlugDJEvents;
import com.goatgoose.bustaplugdj.Model.PlugDJPlayer;
import com.goatgoose.bustaplugdj.Model.Stage;
import com.goatgoose.bustaplugdj.Tasks.PlugDJServerTask;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class BustaPlugDJ extends JavaPlugin {

    private HashMap<String, PlugDJPlayer> plugDJPlayers = new HashMap<String, PlugDJPlayer>();

    private PlayerListener playerListener;

    private PlugDJEvents plugDJEvents;

    private Stage stage;

    @Override
    public void onEnable() {
        playerListener = new PlayerListener(this);
        plugDJEvents = new PlugDJEvents(this);
        stage = new Stage(this);

        for(Player player : Bukkit.getOnlinePlayers()) {
            addPlugDJPlayer(new PlugDJPlayer(player));
        }

        new PlugDJServerTask(this).runTaskAsynchronously(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) {
            // console command
            return false;
        }

        PlugDJPlayer plugDJPlayer = getPlugDJPlayer((Player) sender);

        // /plugdj
        if(command.getName().equalsIgnoreCase("plugdj")) {

            if(args.length == 0) {
                return false;
            }

            // /plugdj setupDisplay
            else if(args[0].equalsIgnoreCase("setupDisplay")) {
                if(args.length != 1) {
                    return false;
                } else {
                    if(getWorldEdit() == null) {
                        return false;
                    }
                    Selection selection = getWorldEdit().getSelection(plugDJPlayer.getPlayer());
                    if(selection == null) {
                        Bukkit.broadcastMessage("No selection found");
                        return false;
                    } else {
                        for(int x = selection.getMinimumPoint().getBlockX(); x <= selection.getMaximumPoint().getBlockX(); x = x + 1) {
                            for(int y = selection.getMinimumPoint().getBlockY(); y <= selection.getMaximumPoint().getBlockY(); y = y + 1) {
                                for(int z = selection.getMinimumPoint().getBlockZ(); z <= selection.getMaximumPoint().getBlockZ(); z = z + 1) {
                                    Block block = selection.getWorld().getBlockAt(x, y, z);
                                    stage.addDisplayBlock(block);
                                }
                            }
                        }
                        plugDJPlayer.getPlayer().sendMessage("display registered");
                        return true;
                    }
                }
            }

            // /plugdj setupFireworks
            else if(args[0].equalsIgnoreCase("setupFireworks")) {
                if(args.length != 1) {
                    return false;
                } else {
                    if(plugDJPlayer.getStatus() == PlugDJPlayer.Status.SETUP_FIREWORKS) {
                        plugDJPlayer.setStatus(PlugDJPlayer.Status.ABSENT);
                        plugDJPlayer.getPlayer().sendMessage("Disabled SETUP_FIREWORKS");
                    } else {
                        plugDJPlayer.setStatus(PlugDJPlayer.Status.SETUP_FIREWORKS);
                        plugDJPlayer.getPlayer().sendMessage("Enabled SETUP_FIREWORKS, click firework launchers to add them");
                    }
                    return true;
                }
            }

            // /plugdj dj
            else if(args[0].equalsIgnoreCase("dj")) {
                if(args.length != 1) {
                    return false;
                } else {
                    plugDJPlayer.setStatus(PlugDJPlayer.Status.DJ);
                    return true;
                }
            }

            // /plugdj join
            else if(args[0].equalsIgnoreCase("join")) {
                if(args.length != 1) {
                    return false;
                } else {
                    plugDJPlayer.setStatus(PlugDJPlayer.Status.AUDIENCE);
                    return true;
                }
            }

        }

        return true;
    }

    public void addPlugDJPlayer(PlugDJPlayer plugDJPlayer) {
        plugDJPlayers.put(plugDJPlayer.getPlayer().getName(), plugDJPlayer);
    }

    public void removePlugDJPlayer(PlugDJPlayer plugDJPlayer) {
        plugDJPlayers.remove(plugDJPlayer.getPlayer().getName());
    }

    public PlugDJPlayer getPlugDJPlayer(Player player) {
        return plugDJPlayers.get(player.getName());
    }

    public Stage getStage() {
        return stage;
    }

    public WorldEditPlugin getWorldEdit() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if(plugin instanceof WorldEditPlugin) return (WorldEditPlugin) plugin;
        else return null;
    }

    public PlugDJEvents getPlugDJEvents() {
        return plugDJEvents;
    }

}
