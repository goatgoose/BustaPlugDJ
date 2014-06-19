package com.goatgoose.bustaplugdj.plugdj.events;

public class ModSkipEvent implements Event {

    private String username;

    public ModSkipEvent(String username) {
        this.username = username;
        manager.getEventListener().onModSkip(this);
    }

    public String getUsername() {
        return username;
    }

}
