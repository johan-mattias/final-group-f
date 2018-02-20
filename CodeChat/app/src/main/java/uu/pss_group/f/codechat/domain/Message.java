package uu.pss_group.f.codechat.domain;


public class Message {
    //Attributes
    private String senderId, receiverId, text;

    //Constructor
    public Message(String senderId, String receiverId, String text) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.text = text;
    }

    //Getters and Setters
    public String getSenderId() {return senderId;}

    public String getReceiverId() {return receiverId;}

    public String getText() {return text;}

    public void setSenderId() {this.senderId = senderId;}

    public void setReceiverId() {this.receiverId = receiverId;}

    public void setText() {this.text = text;}
}

