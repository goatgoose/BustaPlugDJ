package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.User;
import com.goatgoose.bustaplugdj.plugdj.model.Vote;

public class VoteUpdateEvent implements Event {

    private User user;

    private Vote vote;

    public VoteUpdateEvent(User user, Vote vote) {
        this.user = user;
        this.vote = vote;
        manager.getEventListener().onVoteUpdate(this);
    }

    public User getUser() {
        return user;
    }

    public Vote getVote() {
        return vote;
    }

}
