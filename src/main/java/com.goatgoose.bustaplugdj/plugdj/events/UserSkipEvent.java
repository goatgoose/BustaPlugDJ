package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

public class UserSkipEvent implements Event {

    private User user;

    public UserSkipEvent(User user) {
        this.user = user;
        manager.getEventListener().onUserSkip(this);
    }

    public User getUser() {
        return user;
    }
}
