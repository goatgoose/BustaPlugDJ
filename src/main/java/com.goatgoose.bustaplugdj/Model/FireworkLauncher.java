package com.goatgoose.bustaplugdj.Model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.Tasks.LaunchFireworkTask;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.lang.reflect.Field;

public class FireworkLauncher {

    private BustaPlugDJ plugin;

    private Block block;

    public FireworkLauncher(BustaPlugDJ instance, Block block) {
        this.plugin = instance;
        this.block = block;
    }

    public void launchFirework() {
        Firework firework = block.getWorld().spawn(block.getLocation(), Firework.class);
        FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.RED).with(FireworkEffect.Type.BURST).build();
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.clearEffects();
        fireworkMeta.addEffect(effect);

        final int max_power = 2;
        final int min_power = 1;

        Field field;
        try {
            field = fireworkMeta.getClass().getDeclaredField("power");
            field.setAccessible(true);
            field.set(fireworkMeta, min_power + (int)(Math.random() * ((max_power - min_power) + 1)));
        } catch(Exception e) {
            e.printStackTrace();
        }

        firework.setFireworkMeta(fireworkMeta);
    }
}
