package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

public class CurateUpdateEvent implements Event {

    User user;

    public CurateUpdateEvent(User user) {
        this.user = user;
        manager.getEventListener().onCurateUpdate(this);
    }

    public User getUser() {
        return user;
    }

}
