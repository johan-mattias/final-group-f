package uu.pss_group.f.codechat.controllers;

import java.util.List;

import uu.pss_group.f.codechat.database.ConversationDatabaseFetcher;
import uu.pss_group.f.codechat.database.MessageDatabaseFetcher;
import uu.pss_group.f.codechat.demo.Conversation;
import uu.pss_group.f.codechat.demo.Message;
import uu.pss_group.f.codechat.view.ConversationViewer;
import uu.pss_group.f.codechat.view.MessageViewer;

/**
 * Created by rasmus on 2018-02-24.
 */

public class MessageController {
    private MessageViewer view;
    private MessageDatabaseFetcher fetcher;

    public void setView(MessageViewer view) {
        this.view = view;
        view.setup();
        view.register(this);
    }

    public void setDatabase(MessageDatabaseFetcher fetcher) {
        this.fetcher = fetcher;
        fetcher.setup();
        fetcher.register(this);
    }

    public void fetchData(Conversation conv){
        fetcher.fetch(conv);
    }

    public void updateView(List conversations) {
        this.view.update(conversations);
    }

    public void createMessage(String senderID, String recieverID) {
        this.fetcher.createMessage(senderID, recieverID);
    }

    public void updateMessageView(List<Message> m) {
        this.view.update(m);
    }
}
