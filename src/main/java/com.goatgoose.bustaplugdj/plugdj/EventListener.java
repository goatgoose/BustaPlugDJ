package com.goatgoose.bustaplugdj.plugdj;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.model.BustaPlugDJMananger;

public class EventListener {

    private BustaPlugDJMananger manager;

    public EventListener(BustaPlugDJMananger manager) {
        this.manager = manager;
    }

    //public void onChat(PlugDJChat chat) {}

    public void onUserSkip(User user) {

    }

    public void onUserJoin(User user) {

    }

    public void onUserLeave(User user) {

    }

    public void onUserFan(User user) {

    }

    public void onFriendJoin(User user) {

    }
}
