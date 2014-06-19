package com.goatgoose.bustaplugdj.plugdj;

import com.goatgoose.bustaplugdj.model.BustaPlayer;
import com.goatgoose.bustaplugdj.model.BustaPlugDJMananger;
import com.goatgoose.bustaplugdj.plugdj.events.*;
import org.bukkit.Bukkit;

public class EventListener {

    private BustaPlugDJMananger manager;

    public EventListener(BustaPlugDJMananger manager) {
        this.manager = manager;
    }

    public void onChat(UserChatEvent event) {

    }

    public void onUserSkip(UserSkipEvent event) {

    }

    public void onUserJoin(UserJoinEvent event) {

    }

    public void onUserLeave(UserLeaveEvent event) {

    }

    public void onUserFan(UserFanEvent event) {

    }

    public void onFriendJoin(FriendJoinEvent event) {

    }

    public void onFanJoin(FanJoinEvent event) {

    }

    public void onVoteUpdate(VoteUpdateEvent event) {

    }

    public void onCurateUpdate(CurateUpdateEvent event) {

    }

    public void onRoomScoreUpdate(RoomScoreUpdateEvent event) {

    }

    public void onDJAdvance(DJAdvanceEvent event) {

    }

    public void onDJUpdate(DJUpdateEvent event) {
        manager.getCurrentDJ().setStatus(BustaPlayer.Status.AUDIENCE);
        if(!manager.getCurrentDJ().getPlugDJUsername().equalsIgnoreCase(event.getUser().getUsername())) {
            BustaPlayer newDJ = manager.getBustaPlayer(event.getUser().getUsername());
            if(newDJ != null) {
                newDJ.setStatus(BustaPlayer.Status.DJ);
            }
        }
    }

    public void onWaitListUpdate(WaitListUpdateEvent event) {

    }

    public void onVoteSkip(VoteSkipEvent event) {

    }

    public void onModSkip(ModSkipEvent event) {

    }

    public void onChatCommand(ChatCommandEvent event) {

    }

    public void onHistoryUpdate(HistoryUpdateEvent event) {

    }
}
