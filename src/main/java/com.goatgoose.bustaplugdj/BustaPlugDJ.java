package com.goatgoose.bustaplugdj;

import com.goatgoose.bustaplugdj.Model.FireworkGroup;
import com.goatgoose.bustaplugdj.Model.PlugDJPlayer;
import com.goatgoose.bustaplugdj.Model.SQSManager;
import com.goatgoose.bustaplugdj.Model.Stage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class BustaPlugDJ extends JavaPlugin {

    private SQSManager sqsManager;

    private HashMap<Player, PlugDJPlayer> plugDJPlayers = new HashMap<Player, PlugDJPlayer>();

    private HashMap<String, Stage> stages = new HashMap<String, Stage>();

    @Override
    public void onEnable() {
        sqsManager = new SQSManager("802757571659/busta-plug-dj");

        for(Player player : Bukkit.getOnlinePlayers()) {
            addPlugDJPlayer(new PlugDJPlayer(player));
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

            // /plugdj newStage [stage name]
            if(args[0].equalsIgnoreCase("newStage")) {
                if(args.length != 1) {
                    return false;
                } else {
                    Stage stage = new Stage(this, args[1]);
                    addStage(stage);
                    plugDJPlayer.setStage(stage);
                    return true;
                }
            }

            // /plugdj selectStage [stage name]
            else if(args[0].equalsIgnoreCase("selectStage")) {
                if(args.length != 1) {
                    return false;
                } else {
                    if(stages.containsKey(args[1])) {
                        plugDJPlayer.setStage(stages.get(args[1]));
                        return true;
                    } else {
                        return false;
                    }
                }
            }

            // /plugdj newFireworkGroup [fireworkGroup name]
            else if(args[0].equalsIgnoreCase("newFireworkGroup")) {
                if(args.length != 1) {
                    return false;
                } else {
                    Stage stage = plugDJPlayer.getStage();
                    if(stage != null) {
                        stage.addFireworkGroup(new FireworkGroup(args[1]));
                    }
                }
            }

            // /plugdj newFireworkLauncher

        }

        return true;
    }

    public void addPlugDJPlayer(PlugDJPlayer plugDJPlayer) {
        plugDJPlayers.put(plugDJPlayer.getPlayer(), plugDJPlayer);
    }

    public void removePlugDJPlayer(PlugDJPlayer plugDJPlayer) {
        plugDJPlayers.remove(plugDJPlayer.getPlayer());
    }

    public PlugDJPlayer getPlugDJPlayer(Player player) {
        return plugDJPlayers.get(player);
    }

    public void addStage(Stage stage) {
        stages.put(stage.getName(), stage);
    }

}
