package uu.pss_group.f.codechat.database;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.controllers.MessageController;
import uu.pss_group.f.codechat.demo.Conversation;
import uu.pss_group.f.codechat.demo.Message;

public class FirebaseConversationFetcher implements  ConversationDatabaseFetcher, ValueEventListener{


    private Context ctx;
    private ConversationController controller;
    List<Conversation> conversations = new ArrayList<>();
    private MessageController messageController;

    public void setContext(Context context){
        this.ctx = context;
    }

    @Override
    public void setup() {

    }

    @Override
    public void fetch(String convId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("conversations").child(convId);
        ref.addValueEventListener(this);
    }

    @Override
    public void createConversation(String senderID, String recieverIdD) {

    }

    public void registerMessageController(MessageController mock) {
        this.messageController = mock;
    }

    @Override
    public void registerConversationController(ConversationController conversationController) {
        this.controller = conversationController;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        Conversation conv = dataSnapshot.getValue(Conversation.class);

        if(conv != null){
            Log.d("yay", "Fetched conv id is: "+conv.getId());

            conversations.add(conv);

            controller.updateView(conversations);
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void createConversation(Conversation c, String tmpConvId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference();
        String key = ref.child("conversations").getKey();

        Map<String, Object> data = new HashMap<>();

        String id = (String) c.get("id");
        data.put(key+"/"+id+"/", c);

        ref.updateChildren(data);

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void fetchConversationsWith(String userId) {

    }

    @Override
    public void postMessage(String convId, String message, String author) {
        final FirebaseDatabase database;
        database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference();

        String key = ref.getKey();

        Map<String, Object> data = new HashMap<>();
        String id = UUID.randomUUID().toString().substring(0, 10);


        Message m = new Message();
        m.put("author", author);
        m.put("message", message);

        // adds messsage to said conversation
        data.put("conversations/"+convId+"/"+id, m);

        // User joins the conversation
        data.put("members/"+convId+"/"+author, true);

        ref.updateChildren(data);

        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void fetchMessages(String convId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("conversations").child(convId).child("messages");
        ref.addValueEventListener(new ValueEventListener() {
            ArrayList<Message> clazz = new ArrayList<Message>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot msgSnapshot: dataSnapshot.getChildren()) {
                    Message m = msgSnapshot.getValue(Message.class);

                    if(m != null){

                        Log.d("testsuite","Fetched: "+m.getAuthor()+"   \t  message: "+m.getMessage());

                        clazz.add(m);
                        messageController.updateView(clazz);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
