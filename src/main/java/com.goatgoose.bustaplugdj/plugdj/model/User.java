package com.goatgoose.bustaplugdj.plugdj.model;

import java.util.Date;

public class User {

    private String avatarID;

    private boolean curated;

    private int curatorPoints;

    private String dateJoined;

    private int djPoints;

    private int fans;

    private String id;

    private String language;

    private int listenerPoints;

    private int permission;

    private int priority;

    private int relationship;

    private int status;

    private String uIndex;

    private String username;

    private int vote;

    private int wlIndex;

    public String getAvatarID() {
        return avatarID;
    }

    public boolean isCurated() {
        return curated;
    }

    public int getCuratorPoints() {
        return curatorPoints;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public int getDjPoints() {
        return djPoints;
    }

    public int getFans() {
        return fans;
    }

    public String getId() {
        return id;
    }

    public String getLanguage() {
        return language;
    }

    public int getListenerPoints() {
        return listenerPoints;
    }

    public int getPermission() {
        return permission;
    }

    public int getPriority() {
        return priority;
    }

    public int getRelationship() {
        return relationship;
    }

    public int getStatus() {
        return status;
    }

    public String getuIndex() {
        return uIndex;
    }

    public String getUsername() {
        return username;
    }

    public int getVote() {
        return vote;
    }

    public int getWlIndex() {
        return wlIndex;
    }
}
