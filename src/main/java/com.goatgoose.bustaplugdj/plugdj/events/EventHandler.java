package com.goatgoose.bustaplugdj.plugdj.events;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;

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

    public EventHandler(LinkedTreeMap linkedTreeMap) {
        Gson gson = new Gson();
        EventType eventType = EventType.valueOf((String) linkedTreeMap.get("eventType"));
        if(eventType != null) {

            if(eventType == EventType.CHAT) {

            }

        }
    }

}
