package com.goatgoose.bustaplugdj.model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.lang.reflect.Field;

public class FireworkLauncher {

    private Block block;

    public FireworkLauncher(Block block) {
        this.block = block;
    }

    public void launchFirework() {
        Firework firework = block.getWorld().spawn(block.getLocation(), Firework.class);
        FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.RED).with(FireworkEffect.Type.BALL).build();
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

    public Block getBlock() {
        return block;
    }
}
