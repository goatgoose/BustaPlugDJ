package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.Media;
import com.goatgoose.bustaplugdj.plugdj.model.User;

import java.util.ArrayList;

public class DJAdvanceEvent implements Event {

    private ArrayList<User> users;

    private Media media;

    private User dj;

    public DJAdvanceEvent(ArrayList<User> users, Media media, User dj) {
        this.users = users;
        this.media = media;
        this.dj = dj;
        manager.getEventListener().onDJAdvance(this);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Media getMedia() {
        return media;
    }

    public User getDj() {
        return dj;
    }

}
