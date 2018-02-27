package uu.pss_group.f.codechat.database;

import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.controllers.MessageController;
import uu.pss_group.f.codechat.demo.Conversation;

/**
 * Created by rasmus on 2018-02-24.
 */

public interface MessageDatabaseFetcher {
    void setup();

    void fetch(Conversation c);

    void register(MessageController c);

    void createMessage(String senderID, String recieverIdD);
}
