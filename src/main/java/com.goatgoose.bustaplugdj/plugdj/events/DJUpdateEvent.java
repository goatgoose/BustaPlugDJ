package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

public class DJUpdateEvent implements Event {

    private User user;

    public DJUpdateEvent(User user) {
        this.user = user;
        manager.getEventListener().onDJUpdate(this);
    }

    public User getUser() {
        return user;
    }

}
