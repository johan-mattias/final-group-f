package uu.pss_group.f.codechat.controllers;

import java.util.List;

import uu.pss_group.f.codechat.database.ConversationDatabaseFetcher;
import uu.pss_group.f.codechat.view.ConversationViewer;

/**
 * Created by rasmus on 2018-02-21.
 */

public class ConversationController {
    private ConversationViewer view;
    private ConversationDatabaseFetcher fetcher;

    public void setView(ConversationViewer view) {
        this.view = view;
        view.setup();
        view.register(this);
    }

    public void setDatabase(ConversationDatabaseFetcher fetcher) {
        this.fetcher = fetcher;
        fetcher.setup();
        fetcher.register(this);
    }

    public void fetchData(String convId){
        fetcher.fetch(convId);
    }

    public void updateView(List conversations) {
        this.view.update(conversations);
    }

    public void createConversation(String senderID, String recieverID) {
        this.fetcher.createConversation(senderID, recieverID);
    }
}
