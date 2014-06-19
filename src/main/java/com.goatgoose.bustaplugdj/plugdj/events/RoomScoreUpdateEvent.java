package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.RoomScore;

public class RoomScoreUpdateEvent implements Event {

    RoomScore roomScore;

    public RoomScoreUpdateEvent(RoomScore roomScore) {
        this.roomScore = roomScore;
        manager.getEventListener().onRoomScoreUpdate(this);
    }

    public RoomScore getRoomScore() {
        return roomScore;
    }

}
