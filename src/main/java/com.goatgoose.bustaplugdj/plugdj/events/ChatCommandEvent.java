package com.goatgoose.bustaplugdj.plugdj.events;

public class ChatCommandEvent implements Event {

    private String command;

    public ChatCommandEvent(String command) {
        this.command = command;
        manager.getEventListener().onChatCommand(this);
    }

    public String getCommand() {
        return command;
    }

}
