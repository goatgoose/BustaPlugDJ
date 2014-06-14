package com.goatgoose.bustaplugdj.Model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;

import java.util.ArrayList;
import java.util.HashMap;

public class Stage {

    private BustaPlugDJ plugin;

    private String name;

    private HashMap<String, FireworkGroup> fireworkGroups = new HashMap<String, FireworkGroup>();

    public Stage(BustaPlugDJ instance, String name) {
        this.plugin = instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFireworkGroup(FireworkGroup fireworkGroup) {
        fireworkGroups.put(fireworkGroup.getName(), fireworkGroup);
    }

    public void removeFireworkGroup(FireworkGroup fireworkGroup) {
        fireworkGroups.remove(fireworkGroup.getName());
    }

}
