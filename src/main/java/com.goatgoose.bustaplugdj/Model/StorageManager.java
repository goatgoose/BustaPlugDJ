package com.goatgoose.bustaplugdj.model;

public class StorageManager {

    private static StorageManager ourInstance = new StorageManager();

    public static StorageManager getInstance() {
        return ourInstance;
    }

    private StorageManager() {
    }
}
