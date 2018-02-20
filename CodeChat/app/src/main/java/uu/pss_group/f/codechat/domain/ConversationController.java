package uu.pss_group.f.codechat.domain;


public class ConversationController {

    public Message[] loadConversation() {
        Message[] messages = new Message[5];
        messages[0] = new Message("123", "456", "Hi");
        messages[1] = new Message("456", "123", "Hi");
        messages[2] = new Message("123", "456", "How are you?");
        messages[3] = new Message("123", "456", "..");
        messages[4] = new Message("456", "123", "Fine");

        return messages;
    }
}
