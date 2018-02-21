package uu.pss_group.f.codechat.domain;


public class Message {
    //Attributes
    private String senderId, receiverId, text, messageId, conversationId;

    public Message(){ }

    //Constructor
    public Message(String senderId, String receiverId, String text, String messageId, String conversationId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
        this.messageId = messageId;
        this.conversationId = conversationId;
    }

    public Message() {}

    //Getters and Setters


    public String getMessageId() {return messageId;}

    public void setMessageId(String messageId) {this.messageId = messageId;}

    public String getSenderId() {return senderId;}

    public String getReceiverId() {return receiverId;}

    public String getText() {return text;}

    public String getMessageId() {return messageId;}

    public String getConversationId() {return conversationId;}

    public void setSenderId(String senderId) {this.senderId = senderId;}

    public void setReceiverId(String receiverId) {this.receiverId = receiverId;}

    public void setText(String text) {this.text = text;}

    public void setMessageId(String messageId) {this.messageId = messageId;}

    public void setConversationId(String conversationId) {this.conversationId = conversationId;}
}

