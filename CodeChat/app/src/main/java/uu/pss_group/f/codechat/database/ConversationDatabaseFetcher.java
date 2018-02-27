package uu.pss_group.f.codechat.database;

import uu.pss_group.f.codechat.demo.Conversation;
import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.controllers.MessageController;


public interface ConversationDatabaseFetcher {

    void setup();

    void fetch(String convId);

    void createConversation(String senderID, String recieverIdD);

    void postMessage(String convId, String message, String author);

    void fetchMessages(String convId);

    void createConversation(Conversation c, String tmpConvId);

    void fetchConversationsWith(String userId);

    void registerMessageController(MessageController c);

    void registerConversationController(ConversationController conversationController);
}
