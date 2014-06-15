package com.goatgoose.bustaplugdj.Tasks;

import com.goatgoose.bustaplugdj.BustaPlugDJ;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class PlugDJServerTask extends BukkitRunnable {

    BustaPlugDJ plugin;

    public PlugDJServerTask(BustaPlugDJ instance) {
        this.plugin = instance;
    }

    public void run() {
        try {
            ServerSocket listener = new ServerSocket(9090);
            try {
                while (true) {
                    Socket socket = listener.accept();
                    try {
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        while (true) {
                            String data = in.readLine();
                            if(data.length() > 0) {
                                plugin.getPlugDJEvents().onDJUpdate(data);
                                break;
                            }
                        }
                    } finally {
                        socket.close();
                    }
                }
            } finally {
                listener.close();
            }
        } catch(IOException e) {}
    }
}
