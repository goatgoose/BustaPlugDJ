package com.goatgoose.bustaplugdj.model;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

import java.lang.reflect.Field;

public class FireLauncher {

    private Block block;

    public FireLauncher(Block block) {
        this.block = block;
    }

    public void launchFire() {
        Firework firework = block.getWorld().spawn(block.getLocation(), Firework.class);
        FireworkEffect effect = FireworkEffect.builder().trail(true).flicker(false).withColor(Color.RED).with(FireworkEffect.Type.BURST).build();
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.clearEffects();
        fireworkMeta.addEffect(effect);

        Field field;
        try {
            field = fireworkMeta.getClass().getDeclaredField("power");
            field.setAccessible(true);
            field.set(fireworkMeta, 0);
        } catch(Exception e) {
            //e.printStackTrace();
        }

        firework.setFireworkMeta(fireworkMeta);
    }

}
