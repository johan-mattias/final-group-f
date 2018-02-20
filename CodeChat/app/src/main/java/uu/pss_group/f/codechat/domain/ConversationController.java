package uu.pss_group.f.codechat.domain;


public class ConversationController {

    public Message[] loadConversation(String conversationId, int lastMessage) {
        Message[] messages = new Message[20];
        messages[0] = new Message("123", "456", "Hi", "1");
        messages[1] = new Message("456", "123", "Hi", "2");
        messages[2] = new Message("123", "456", "How are you?", "3");
        messages[3] = new Message("123", "456", "..", "4");
        messages[4] = new Message("456", "123", "Fine", "5");
        messages[5] = new Message("123", "456", "Hi", "6");
        messages[6] = new Message("456", "123", "Hi", "7");
        messages[7] = new Message("123", "456", "How are you?", "8");
        messages[8] = new Message("123", "456", "..", "9");
        messages[9] = new Message("456", "123", "Fine", "10");
        messages[10] = new Message("123", "456", "Hi", "11");
        messages[11] = new Message("456", "123", "Hi", "12");
        messages[12] = new Message("123", "456", "How are you?", "13");
        messages[13] = new Message("123", "456", "..", "14");
        messages[14] = new Message("456", "123", "Fine", "15");
        messages[15] = new Message("123", "456", "Hi", "16");
        messages[16] = new Message("456", "123", "Hi", "17");
        messages[17] = new Message("123", "456", "How are you?", "18");
        messages[18] = new Message("123", "456", "This is something long that takes more than one line. But its meaning is completely useless. Thanks for reading", "19");
        messages[19] = new Message("456", "123", "This is the last message", "20");

        return messages;
    }
}
