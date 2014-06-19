package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.MessageContainer;
import com.goatgoose.bustaplugdj.plugdj.model.User;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

public class EventHandler {

    public enum EventType {
        CHAT,
        USER_SKIP,
        USER_JOIN,
        USER_LEAVE,
        USER_FAN,
        FRIEND_JOIN,
        FAN_JOIN,
        VOTE_UPDATE,
        CURATE_UPDATE,
        ROOM_SCORE_UPDATE,
        DJ_ADVANCE,
        DJ_UPDATE,
        WAIT_LIST_UPDATE,
        VOTE_SKIP,
        MOD_SKIP,
        CHAT_COMMAND,
        HISTORY_UPDATE
    }

    public EventHandler(LinkedTreeMap linkedTreeMap, String message) {
        Gson gson = new Gson();
        EventType eventType = EventType.valueOf((String) linkedTreeMap.get("eventType"));
        if(eventType != null) {

            // TODO
            // check for every event

            if(eventType == EventType.DJ_UPDATE) {
                new DJUpdateEvent(gson.fromJson(message, User.class));
            }

        }
    }

}
