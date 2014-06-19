package com.goatgoose.bustaplugdj;

import com.goatgoose.bustaplugdj.eventHandlers.PlayerListener;
import com.goatgoose.bustaplugdj.model.BustaPlayer;
import com.goatgoose.bustaplugdj.plugdj.EventListener;
import com.goatgoose.bustaplugdj.plugdj.SocketHandler;
import com.goatgoose.bustaplugdj.model.Stage;
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

import java.util.HashMap;

public class BustaPlugDJ extends JavaPlugin {

    // TODO
    // - Add icons to each action (ie firework for launchFireworks, torch for lights)
    // - Add fire action that constantly emits red fireworks until turned off
    // - Separate display into 3 sections, and light up each part depending on f coord
    // - Firework groups with multiple icons to emit each of them separately
    // - Firework colors with a popup menu to select them
    // - Full plug.dj EventListener implementation in minecraft
    // - Minecraft username verification in plug.dj
    // - Teleport on dj change (dj to stage, audience to dance floor)
    // - Minecraft scoreboard of plug.dj room/dj info and countdown to you djing
    // - Plug.dj and minecraft chat sync (only in stage)
    // - Fake npc audience and djs for plug.dj users who aren't in minecraft (dancing in minecraft based on wooting)
    // - BustaPlugDJClient.js running on every mc client to control plug.dj individually (ie volume control, wooting and mehing, song select) and new TCP server to handle them
    //   - Auto woot/meh based on how much you're dancing in minecraft xD
    //   - DJ would have control over everyone's volume, so add options to fade in/out and boost for drops
    // - Competition mode that allows users to take turns competing for votes
    // - Commandless user interface for joining/djing/leaving stage
    // - Save setup to file and parse in onEnable()
    // - Better dance floor with lights
    // - More options for display than just flashDisplay()

    private HashMap<String, BustaPlayer> plugDJPlayers = new HashMap<String, BustaPlayer>();

    private PlayerListener playerListener;

    private Stage stage;

    @Override
    public void onEnable() {
        playerListener = new PlayerListener(this);
        eventListener = new EventListener(this);
        stage = new Stage(this);

        for(Player player : Bukkit.getOnlinePlayers()) {
            addPlugDJPlayer(new BustaPlayer(player));
        }

        Server socketServer = new Server(8025);
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

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if(!(sender instanceof Player)) {
            // console command
            return false;
        }

        BustaPlayer bustaPlayer = getPlugDJPlayer((Player) sender);

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
                        bustaPlayer.getPlayer().sendMessage("display registered");
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
                        bustaPlayer.getPlayer().sendMessage("Disabled SETUP_FIREWORKS");
                    } else {
                        bustaPlayer.setStatus(BustaPlayer.Status.SETUP_FIREWORKS);
                        bustaPlayer.getPlayer().sendMessage("Enabled SETUP_FIREWORKS, click firework launchers to add them");
                    }
                    return true;
                }
            }

            // /plugdj dj
            else if(args[0].equalsIgnoreCase("dj")) {
                if(args.length != 1) {
                    return false;
                } else {
                    bustaPlayer.setStatus(BustaPlayer.Status.DJ);
                    return true;
                }
            }

            // /plugdj join
            else if(args[0].equalsIgnoreCase("join")) {
                if(args.length != 1) {
                    return false;
                } else {
                    bustaPlayer.setStatus(BustaPlayer.Status.AUDIENCE);
                    return true;
                }
            }

        }

        return true;
    }

    public void addPlugDJPlayer(BustaPlayer bustaPlayer) {
        plugDJPlayers.put(bustaPlayer.getPlayer().getName(), bustaPlayer);
    }

    public void removePlugDJPlayer(BustaPlayer bustaPlayer) {
        plugDJPlayers.remove(bustaPlayer.getPlayer().getName());
    }

    public BustaPlayer getPlugDJPlayer(Player player) {
        return plugDJPlayers.get(player.getName());
    }

    public EventListener getEventListener() {
        return eventListener;
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