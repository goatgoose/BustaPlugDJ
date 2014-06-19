package com.goatgoose.bustaplugdj.plugdj.events;

public class VoteSkipEvent implements Event {

    public VoteSkipEvent() {
        manager.getEventListener().onVoteSkip(this);
    }

}
