package com.goatgoose.bustaplugdj.plugdj;

import org.bukkit.Bukkit;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class PlugDJSocketHandler {

    private final String prefix = "[BustaPlugDJ] [WebSocketServer] ";

    @OnWebSocketConnect
    public void onConnect(Session session) {
        Bukkit.getLogger().info(prefix + "Client connected: " + session.getRemoteAddress().getAddress());
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        Bukkit.getLogger().info(prefix + "Client disconnected: statusCode = " + statusCode + ", reason = " + reason);
    }

    @OnWebSocketError
    public void onError(Throwable throwable) {
        Bukkit.getLogger().warning(prefix + "Error: " + throwable.getMessage());
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        Bukkit.getLogger().info("Message: " + message);
    }

}
