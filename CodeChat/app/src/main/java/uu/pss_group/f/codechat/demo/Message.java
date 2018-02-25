package uu.pss_group.f.codechat.demo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rasmus on 2018-02-24.
 */

public class Message extends HashMap<String, String> {

    private String author;
    private String message;

    public Message(){}

    public Message(String author, String message){
        this.author = author;
        this.message = message;
    }

    public static Message create(String message, String author){
        Message m = new Message();
        m.put("author", author);
        m.put("message", message);

        return m;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        put("author", author);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        put("message",message);
    }
}
