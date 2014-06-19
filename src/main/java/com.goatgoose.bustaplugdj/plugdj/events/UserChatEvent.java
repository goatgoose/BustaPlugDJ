package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.MessageContainer;

public class UserChatEvent implements Event {

    private MessageContainer messageContainer;

    public UserChatEvent(MessageContainer messageContainer) {
        this.messageContainer = messageContainer;
        manager.getEventListener().onChat(this);
    }

    public MessageContainer getMessageContainer() {
        return messageContainer;
    }
}
