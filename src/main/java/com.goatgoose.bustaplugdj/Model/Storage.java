package com.goatgoose.bustaplugdj.model;

import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class Storage {

    private World world;

    private ArrayList<Coordinate> displayBlocks;

    private ArrayList<Coordinate> fireworkBlocks;

    private ArrayList<Coordinate> fireBlocks;

    public Storage(World world) {
        this.world = world;
    }

    public void setDisplayBlocks(ArrayList<Block> blocks) {
        for(Block block : blocks) {
            displayBlocks.add(new Coordinate(block));
        }
    }

    public ArrayList<Block> getDisplayBlocks() {
        ArrayList<Block> blocks = new ArrayList<Block>();
        for(Coordinate coordinate : displayBlocks) {
            blocks.add(coordinate.getBlock(world));
        }
        return blocks;
    }

    public void setFireworkBlocks(ArrayList<FireworkLauncher> fireworkLaunchers) {
        for(FireworkLauncher fireworkLauncher : fireworkLaunchers) {
            fireworkBlocks.add(new Coordinate(fireworkLauncher.getBlock()));
        }
    }

    public ArrayList<FireworkLauncher> getFireworkLaunchers() {
        ArrayList<FireworkLauncher> fireworkLaunchers = new ArrayList<FireworkLauncher>();
        for(Coordinate coordinate : fireworkBlocks) {
            fireworkLaunchers.add(new FireworkLauncher(coordinate.getBlock(world)));
        }
        return fireworkLaunchers;
    }

    public void setFireBlocks(ArrayList<FireLauncher> fireLaunchers) {
        for(FireLauncher fireLauncher : fireLaunchers) {
            fireBlocks.add(new Coordinate(fireLauncher.getBlock()));
        }
    }

    public ArrayList<FireLauncher> getFireLaunchers() {
        ArrayList<FireLauncher> fireLaunchers = new ArrayList<FireLauncher>();
        for(Coordinate coordinate : fireBlocks) {
            fireLaunchers.add(new FireLauncher(coordinate.getBlock(world)));
        }
        return fireLaunchers;
    }

}
