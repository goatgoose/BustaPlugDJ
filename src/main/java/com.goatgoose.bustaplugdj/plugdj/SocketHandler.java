package com.goatgoose.bustaplugdj.plugdj;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import com.goatgoose.bustaplugdj.plugdj.events.EventHandler;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.bukkit.Bukkit;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class SocketHandler {

    private final String prefix = "[BustaPlugDJ] [WebSocketServer] ";

    @OnWebSocketConnect
    public void onConnect(Session session) {
        //Bukkit.broadcastMessage(prefix + "SOCKET_CONNECT: " + session.getRemoteAddress());
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
    }

    @OnWebSocketError
    public void onError(Throwable throwable) {
        Bukkit.getLogger().warning(prefix + "Error: " + throwable.getMessage());
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        Gson gson = new Gson();
        Object messageJson = gson.fromJson(message, Object.class);
        LinkedTreeMap messageTreeMap = (LinkedTreeMap) messageJson;
        if(messageTreeMap.containsKey("eventType")) {
            new EventHandler(messageTreeMap, message);
        }
    }

}
