package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

public class FanJoinEvent implements Event {

    private User user;

    public FanJoinEvent(User user) {
        this.user = user;
        manager.getEventListener().onFanJoin(this);
    }

    public User getUser() {
        return user;
    }

}
