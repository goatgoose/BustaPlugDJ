package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

import java.util.ArrayList;

public class WaitListUpdateEvent implements Event {

    private ArrayList<User> users;

    public WaitListUpdateEvent(ArrayList<User> users) {
        this.users = users;
        manager.getEventListener().onWaitListUpdate(this);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

}
