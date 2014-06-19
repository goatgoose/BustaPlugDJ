package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.Media;
import com.goatgoose.bustaplugdj.plugdj.model.User;

import java.util.ArrayList;

public class DJAdvanceEvent implements Event {

    private ArrayList<User> users;

    private Media media;

    public DJAdvanceEvent(ArrayList<User> users, Media media) {
        this.users = users;
        this.media = media;
        manager.getEventListener().onDJAdvance(this);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Media getMedia() {
        return media;
    }

}
