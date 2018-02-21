package uu.pss_group.f.codechat.database;

import java.util.List;

import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.domain.Conversation;

public interface ConversationDatabaseFetcher {

    public void setup();

    public void fetch(String convId);

    void register(ConversationController c);

    void createConversation(String senderID, String recieverIdD);
}
