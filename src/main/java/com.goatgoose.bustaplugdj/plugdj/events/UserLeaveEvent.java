package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

public class UserLeaveEvent implements Event {

    User user;

    public UserLeaveEvent(User user) {
        this.user = user;
        manager.getEventListener().onUserLeave(this);
    }

    public User getUser() {
        return user;
    }

}
