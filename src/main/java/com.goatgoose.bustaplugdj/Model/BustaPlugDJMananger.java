package com.goatgoose.bustaplugdj.model;

import com.goatgoose.bustaplugdj.plugdj.EventListener;

public class BustaPlugDJMananger {

    private static final BustaPlugDJMananger INSTANCE = new BustaPlugDJMananger();

    private EventListener eventListener = new EventListener();

}
