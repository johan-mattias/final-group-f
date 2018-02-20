package uu.pss_group.f.codechat.domain;


public class Conversation {
    //Attributes
    private String creatorId, receiverId;

    //Constructor
    public Conversation(String creatorId, String receiverId) {
        this.creatorId = creatorId;
        this.receiverId = receiverId;
    }

    //Getters and Setters
    protected String getCreatorId() {return creatorId;}

    protected void setCreatorId(String creatorId) {this.creatorId = creatorId;}

    protected String getReceiverId() {return receiverId;}

    protected void setReceiverId(String receiverId) {this.receiverId = receiverId;}
}
