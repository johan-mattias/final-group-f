package uu.pss_group.f.codechat.domain;


import java.util.HashMap;
import java.util.Map;

public class Conversation {
    //Attributes
    private String conversationId, creatorId, receiverId;
    private int messageCount;

    public Conversation(){}

    public Conversation(String creatorId, String receiverId) {
        this.conversationId = creatorId + "&&&" + receiverId;
        this.creatorId = creatorId;
        this.receiverId = receiverId;
        this.messageCount = 0;
    }

    //Getters and Setters
    public String getCreatorId() {return creatorId;}

    protected void setCreatorId(String creatorId) {this.creatorId = creatorId;}

    public String getReceiverId() {return receiverId;}

    protected void setReceiverId(String receiverId) {this.receiverId = receiverId;}

    protected int getMessageCount() {return messageCount;}

    protected void setMessageCount(int messageCount) {this.messageCount = messageCount;}

    protected String getConversationId() {return conversationId;}

    public Map<String,Object> toMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("senderId", creatorId);
        map.put("recieverId", receiverId);

        return map;
    }
}
