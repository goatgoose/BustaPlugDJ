package com.goatgoose.bustaplugdj;

import com.goatgoose.bustaplugdj.EventHandlers.PlayerListener;
import com.goatgoose.bustaplugdj.Model.PlugDJPlayer;
import com.goatgoose.bustaplugdj.Model.PlugDJServerEndpoint;
import com.goatgoose.bustaplugdj.Model.Stage;
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
import org.glassfish.tyrus.server.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BustaPlugDJ extends JavaPlugin {

    // TODO
    // - Add icons to each action (ie firework for launchFireworks, torch for lights)
    // - Add fire action that constantly emits red fireworks until turned off
    // - Separate display into 3 sections, and light up each part depending on f coord
    // - Firework groups with multiple icons to emit each of them separately
    // - Firework colors with a popup menu to select them
    // - Full plug.dj API implementation in minecraft
    // - Minecraft username verification in plug.dj
    // - Teleport on dj change (dj to stage, audience to dance floor)
    // - Minecraft scoreboard of plug.dj room/dj info and countdown to you djing
    // - Plug.dj and minecraft chat sync (only in stage)
    // - Fake npc audience and djs for plug.dj users who aren't in minecraft (dancing in minecraft based on wooting)
    // - BustaPlugDJClient.js running on every mc client to control plug.dj individually (ie volume control, wooting and mehing, song select) and new TCP server to handle them
    //   - Auto woot based on how much you're dancing in minecraft xD
    // - Competition mode that allows users to compete for votes head to head
    // - Commandless user interface for joining/djing/leaving stage

    private HashMap<String, PlugDJPlayer> plugDJPlayers = new HashMap<String, PlugDJPlayer>();

    private PlayerListener playerListener;

    private Stage stage;

    @Override
    public void onEnable() {
        playerListener = new PlayerListener(this);
        stage = new Stage(this);

        for(Player player : Bukkit.getOnlinePlayers()) {
            addPlugDJPlayer(new PlugDJPlayer(player));
        }

        Server webSocketServer = new Server("localhost", 8025, "/websockets", PlugDJServerEndpoint.class);

        try {
            webSocketServer.start();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
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

}