package uu.pss_group.f.codechat.domain;
import uu.pss_group.f.codechat.view.ViewController;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.*;

public class ConversationController {
    private FirebaseAuth firebaseAuth;
    private  ArrayList msg;

    private ViewController viewController;

    public ConversationController(){
    }

    public ConversationController(ViewController viewController){
        this.viewController = viewController;
    }

    public void loadConversation() {
        //String conviD = conv.getCreatorId() + "&&&" + conv.getReceiverId();
        msg = new ArrayList<Message>();

        firebaseAuth = FirebaseAuth.getInstance();
        String userId = firebaseAuth.getUid();

        // get reference
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

        // To be done!
        //String keyInMap = "messages/" + conviD + "/"+conv.getMessageCount();
        String keyInMap = "messages/" + userId + "/fakes";

        ref = database.getReference(keyInMap);

        getMessagesBy(ref, userId);
    }



    private void getMessagesBy(DatabaseReference ref, final String userID){
        ref.addValueEventListener(new ValueEventListener() {

            List<Message> messages = new ArrayList<Message>();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                 Message m = dataSnapshot.getValue(Message.class);
                 messages.add(m);
                 Message[] msgs = messages.toArray(new Message[0]);
                 viewController.refresh(msgs);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

    });
 }
}