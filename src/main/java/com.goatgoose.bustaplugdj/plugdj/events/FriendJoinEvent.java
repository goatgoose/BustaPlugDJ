package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;

public class FriendJoinEvent implements Event {

    private User user;

    public FriendJoinEvent(User user) {
        this.user = user;
        manager.getEventListener().onFriendJoin(this);
    }

    public User getUser() {
        return user;
    }

}
