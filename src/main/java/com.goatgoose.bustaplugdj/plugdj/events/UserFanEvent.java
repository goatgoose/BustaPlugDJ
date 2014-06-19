package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

public class UserFanEvent implements Event {

    User user;

    public UserFanEvent(User user) {
        this.user = user;
        manager.getEventListener().onUserFan(this);
    }

    public User getUser() {
        return user;
    }

}
