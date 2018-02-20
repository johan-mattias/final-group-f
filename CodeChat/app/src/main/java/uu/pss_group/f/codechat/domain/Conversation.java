package uu.pss_group.f.codechat.domain;


public class Conversation {
    //Attributes
    private String conversationId, creatorId, receiverId;
    private int messageCount;

    //Constructor
    public Conversation(String creatorId, String receiverId) {
        this.conversationId = creatorId + "&&&" + receiverId;
        this.creatorId = creatorId;
        this.receiverId = receiverId;
        this.messageCount = 0;
    }

    //Getters and Setters
    protected String getCreatorId() {return creatorId;}

    protected void setCreatorId(String creatorId) {this.creatorId = creatorId;}

    protected String getReceiverId() {return receiverId;}

    protected void setReceiverId(String receiverId) {this.receiverId = receiverId;}

    protected int getMessageCount() {return messageCount;}

    protected void setMessageCount(int messageCount) {this.messageCount = messageCount;}

    protected String getConversationId() {return conversationId;}
}
