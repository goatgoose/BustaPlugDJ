package com.goatgoose.bustaplugdj.plugdj.events;

import com.goatgoose.bustaplugdj.plugdj.model.HistoryItem;

import java.util.ArrayList;

public class HistoryUpdateEvent implements Event {

    private ArrayList<HistoryItem> historyItems;

    public HistoryUpdateEvent(ArrayList<HistoryItem> historyItems) {
        this.historyItems = historyItems;
        manager.getEventListener().onHistoryUpdate(this);
    }

    public ArrayList<HistoryItem> getHistoryItems() {
        return historyItems;
    }

}
