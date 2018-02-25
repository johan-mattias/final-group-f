package uu.pss_group.f.codechat.demo;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Conversation extends HashMap<String, Object> {

    private String id;
    private ArrayList<Message> messages;

    public Conversation(){}

    public Conversation(String id, ArrayList messages){
        this.id = id;
        this.messages = messages;
    }

    public static Conversation create(){
        String id = UUID.randomUUID().toString().substring(0,15);

        Conversation c = new Conversation();
        c.put("id", id);
        c.setId(id);

        ArrayList<Message> messages = new ArrayList<>();
        messages.add(Message.create("A new fresh conversation!","Doggo dog"));
        c.put("messages",messages);

        return c;
    }

    public void addMessage(String text){
        Message m = new Message();
        String id = UUID.randomUUID().toString();

        m.put(id, text);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        put("id",id);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
        put("messages", messages);
    }
}
