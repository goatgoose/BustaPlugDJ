package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

public class UserJoinEvent implements Event {

    private User user;

    public UserJoinEvent(User user) {
        this.user = user;
        manager.getEventListener().onUserJoin(this);
    }

    public User getUser() {
        return user;
    }

}
