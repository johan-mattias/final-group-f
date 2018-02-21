package uu.pss_group.f.codechat.database;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.domain.Conversation;

public class FirebaseConversationFetcher implements  ConversationDatabaseFetcher, ValueEventListener{


    private Context ctx;
    private ConversationController controller;
    List<Conversation> conversations = new ArrayList<>();

    public void setContext(Context context){
        this.ctx = context;
    }

    @Override
    public void setup() {

    }

    @Override
    public void fetch(String convId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("conversations/"+convId);
        ref.addValueEventListener(this);
    }

    @Override
    public void register(ConversationController c) {
        this.controller = c;
    }

    @Override
    public void createConversation(String senderID, String recieverIdD) {

        // get reference
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference();

        Conversation c = new Conversation(senderID, recieverIdD);
        Map<String, Object>conversationData = c.toMap();

        Map<String, Object> theConversation = new HashMap<>();
        theConversation.put("/test/conversations/"+senderID, conversationData );

        Map<String, Object> theConversationMirror = new HashMap<>();
        theConversationMirror.put("/test/conversations/"+recieverIdD, conversationData );

        ref.updateChildren(theConversation);
        ref.updateChildren(theConversationMirror);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Conversation conv = dataSnapshot.getValue(Conversation.class);
        //Log.d("Fetched", "Fetched conv from: "+conv.getCreatorId());
        conversations.add(conv);

        controller.updateView(conversations);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
}
