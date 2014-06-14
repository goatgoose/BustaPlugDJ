package com.goatgoose.bustaplugdj;

import com.goatgoose.bustaplugdj.Model.SQSManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class BustaPlugDJ extends JavaPlugin {

    SQSManager sqsManager;

    @Override
    public void onEnable() {
        sqsManager = new SQSManager("802757571659/busta-plug-dj");
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        return true;
    }

}
