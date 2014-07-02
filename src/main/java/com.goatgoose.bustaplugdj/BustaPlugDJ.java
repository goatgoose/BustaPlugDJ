package com.goatgoose.bustaplugdj;

import com.goatgoose.bustaplugdj.eventHandlers.PlayerListener;
import com.goatgoose.bustaplugdj.model.BustaPlayer;
import com.goatgoose.bustaplugdj.model.BustaPlugDJMananger;
import com.goatgoose.bustaplugdj.plugdj.SocketHandler;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class BustaPlugDJ extends JavaPlugin {

    // TODO
    // - Add blocks to each action in toolbar (ie firework for launchFireworks, torch for lights)
    // - Separate display into 3 sections, and light up each part depending on f coord
    // - Firework groups with multiple icons to emit each of them separately
    // - Firework colors with a popup menu to select them
    // - Full plug.dj api implementation in java
    // - Minecraft ip username verification in plug.dj
    // - Minecraft scoreboard of plug.dj room/dj info and countdown to you djing
    // - Plug.dj and minecraft chat sync (only in stage)
    // - Fake npc audience and djs for plug.dj users who aren't in minecraft (dancing in minecraft based on wooting)
    // - BustaPlugDJClient.js running on every mc client to control plug.dj individually (ie volume control, wooting and mehing, song select) and new TCP server to handle them
    //   - Auto woot/meh based on how much you're dancing in minecraft
    //   - DJ would have control over everyone's volume, so add options to fade in/out and boost for drops
    // - Competition mode that allows users to take turns competing for votes
    // - Commandless user interface for joining/djing/leaving stage
    // - Better dance floor with lights
    // - More options for display than just flashing
    // - Multiple stages and stage naming
    // - Stage save to json file on exit, and reload on startup automatically
    // - /plugdj dj forces 2+ djs at the same time
    // - Auto deploy ec2 instance with web socket client and shut it off when it's not being used
    //   - Make an AMI with startup script to launch plug.dj with client
    //   - Configure aws ec2 dependency in java
    // - Dancefloor sparkles

    private PlayerListener playerListener;

    private static BustaPlugDJMananger manager;

    private Server socketServer;

    @Override
    public void onEnable() {
        new BustaPlugDJMananger(this);
        manager = BustaPlugDJMananger.getInstance();

        playerListener = new PlayerListener(this);

        socketServer = new Server(8025);
        WebSocketHandler webSocketHandler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory webSocketServletFactory) {
                webSocketServletFactory.register(SocketHandler.class);
            }
        };
        socketServer.setHandler(webSocketHandler);
        try {
            socketServer.start();
        } catch(Exception e) {
            getLogger().warning("Unable to start socket server");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        try {
            socketServer.stop();
        } catch(Exception e) {
            getLogger().warning("Unable to start socket server");
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) {
            // console command
            return false;
        }

        BustaPlayer bustaPlayer = manager.getBustaPlayer((Player) sender);

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
                    Selection selection = getWorldEdit().getSelection(bustaPlayer.getPlayer());
                    if(selection == null) {
                        bustaPlayer.getPlayer().sendMessage(manager.prefix + "No selection found");
                        return false;
                    } else {
                        for(int x = selection.getMinimumPoint().getBlockX(); x <= selection.getMaximumPoint().getBlockX(); x = x + 1) {
                            for(int y = selection.getMinimumPoint().getBlockY(); y <= selection.getMaximumPoint().getBlockY(); y = y + 1) {
                                for(int z = selection.getMinimumPoint().getBlockZ(); z <= selection.getMaximumPoint().getBlockZ(); z = z + 1) {
                                    Block block = selection.getWorld().getBlockAt(x, y, z);
                                    manager.getStage().addDisplayBlock(block);
                                }
                            }
                        }
                        bustaPlayer.getPlayer().sendMessage(manager.prefix + "Display added");
                        return true;
                    }
                }
            }

            // /plugdj setupFireworks
            else if(args[0].equalsIgnoreCase("setupFireworks")) {
                if(args.length != 1) {
                    return false;
                } else {
                    if(bustaPlayer.getStatus() == BustaPlayer.Status.SETUP_FIREWORKS) {
                        bustaPlayer.setStatus(BustaPlayer.Status.ABSENT);
                        bustaPlayer.getPlayer().sendMessage(manager.prefix + "Disabled SETUP_FIREWORKS");
                    } else {
                        bustaPlayer.setStatus(BustaPlayer.Status.SETUP_FIREWORKS);
                        bustaPlayer.getPlayer().sendMessage(manager.prefix + "Enabled SETUP_FIREWORKS, click firework launchers to add them");
                    }
                    return true;
                }
            }

            // /plugdj setupFire
            else if(args[0].equalsIgnoreCase("setupFire")) {
                if(args.length != 1) {
                    return false;
                } else {
                    if(bustaPlayer.getStatus() == BustaPlayer.Status.SETUP_FIRE) {
                        bustaPlayer.setStatus(BustaPlayer.Status.ABSENT);
                        bustaPlayer.getPlayer().sendMessage(manager.prefix + "Disabled SETUP_FIRE");
                    } else {
                        bustaPlayer.setStatus(BustaPlayer.Status.SETUP_FIRE);
                        bustaPlayer.getPlayer().sendMessage(manager.prefix + "Enabled SETUP_FIRE, click fire launchers to add them");
                    }
                    return true;
                }
            }

            // /plugdj setDJSpawn
            else if(args[0].equalsIgnoreCase("setDJSpawn")) {
                if(args.length != 1) {
                    return false;
                } else {
                    manager.setDjSpawn(bustaPlayer.getPlayer().getLocation());
                    bustaPlayer.getPlayer().sendMessage(manager.prefix + "Set DJ Spawn");
                }
            }

            // /plugdj setAudienceSpawn
            else if(args[0].equalsIgnoreCase("setAudienceSpawn")) {
                if(args.length != 1) {
                    return false;
                } else {
                    manager.setAudienceSpawn(bustaPlayer.getPlayer().getLocation());
                    bustaPlayer.getPlayer().sendMessage(manager.prefix + "Set Audience Spawn");
                }
            }

            // /plugdj dj
            else if(args[0].equalsIgnoreCase("dj")) {
                if(args.length != 1) {
                    return false;
                } else {
                    if(bustaPlayer.getPlayer().hasPermission("plugdj.debug")) {
                        bustaPlayer.setStatus(BustaPlayer.Status.DJ);
                    } else {
                        bustaPlayer.getPlayer().sendMessage(manager.prefix + "You do not have permission to do that.");
                    }
                    return true;
                }
            }

            // /plugdj join
            else if(args[0].equalsIgnoreCase("join")) {
                if(args.length != 1) {
                    return false;
                } else {
                    bustaPlayer.setStatus(BustaPlayer.Status.AUDIENCE);
                    bustaPlayer.getPlayer().sendMessage(manager.prefix + "You joined the plugdj audience!");
                    return true;
                }
            }

            // /plugdj leave
            else if(args[0].equalsIgnoreCase("leave")) {
                if(args.length != 1) {
                    return false;
                } else {
                    bustaPlayer.setStatus(BustaPlayer.Status.ABSENT);
                    bustaPlayer.getPlayer().sendMessage(manager.prefix + "You left the plugdj audience!");
                    return true;
                }
            }

            // /plugdj setName [name]
            else if(args[0].equalsIgnoreCase("setName")) {
                if(args.length != 2) {
                    return false;
                } else {
                    bustaPlayer.setPlugDJUsername(args[1]);
                    bustaPlayer.getPlayer().sendMessage(manager.prefix + "Your plugdj name has been set to " + args[1]);
                }
            }

        }

        return true;
    }

    public WorldEditPlugin getWorldEdit() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if(plugin instanceof WorldEditPlugin) return (WorldEditPlugin) plugin;
        else return null;
    }

}