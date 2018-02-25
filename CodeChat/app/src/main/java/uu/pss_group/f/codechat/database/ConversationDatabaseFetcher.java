package uu.pss_group.f.codechat.database;

import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.demo.Conversation;

public interface ConversationDatabaseFetcher {

    public void setup();

    public void fetch(String convId);

    void register(ConversationController c);

    void createConversation(String senderID, String recieverIdD);

    void createConversation(Conversation c, String tmpConvId);

    void fetchConversationsWith(String userId);
}
