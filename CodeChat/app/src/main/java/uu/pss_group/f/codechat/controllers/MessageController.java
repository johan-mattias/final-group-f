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
    private ConversationDatabaseFetcher fetcher;

    public void setView(MessageViewer view) {
        this.view = view;
        view.setup();
        view.register(this);
    }

    public void setFetcher(ConversationDatabaseFetcher fetcher) {
        this.fetcher = fetcher;
        fetcher.setup();
        fetcher.registerMessageController(this);
    }

    public void fetchData(Conversation conv){
        fetcher.fetch(conv.getId());
    }

    public void updateView(List conversations) {
        this.view.update(conversations);
    }

    public void createMessage(Conversation conv, String senderID, String msg) {
        this.fetcher.postMessage(conv.getId(), senderID, msg);
    }

}
