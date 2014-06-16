package com.goatgoose.bustaplugdj.Model;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import org.bukkit.Bukkit;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/plugdj")
public class PlugDJServerEndpoint {

    @OnOpen
    public void onOpen(Session session) {
        Bukkit.broadcastMessage("Connected: " + session.getId());
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        Bukkit.broadcastMessage("Message: " + message);
        try {
            session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Message received"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        Bukkit.broadcastMessage("Session " + session.getId() + " closed because of " + closeReason);
    }
}
