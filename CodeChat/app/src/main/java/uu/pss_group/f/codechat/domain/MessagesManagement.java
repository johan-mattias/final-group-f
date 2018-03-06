package uu.pss_group.f.codechat.domain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uu.pss_group.f.codechat.R;
import uu.pss_group.f.codechat.data.MyDatabase;
import uu.pss_group.f.codechat.view.ConversationActivity;

/**
 * Created by vilhjalmr on 06/03/2018.
 */

public class MessagesManagement {
    //Attributes
    private MyDatabase database;

    //Constructors
    protected MessagesManagement(MyDatabase database) {
        this.database = database;
    }

    protected void sendMessage(final ConversationActivity activity, final Context caller, final String conversationId, final String message, final String senderId, final String receiverId) {
        database.getDatabaseController().child("conversations").child(senderId).child(conversationId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Conversation conv = snapshot.getValue(Conversation.class);
                    String messageId = Integer.toString(conv.getMessageCount()+1);
                    conv.setMessageCount(conv.getMessageCount()+1);
                    database.getDatabaseController().child("conversations").child(senderId).child(conversationId).setValue(conv).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(caller, caller.getString(R.string.en_error_msg), Toast.LENGTH_LONG).show();
                        }
                    });
                    database.getDatabaseController().child("conversations").child(receiverId).child(conversationId).setValue(conv).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(caller, caller.getString(R.string.en_error_msg), Toast.LENGTH_LONG).show();
                        }
                    });
                    Message msg = new Message(senderId, receiverId, message, messageId, conversationId);
                    database.getDatabaseController().child("messages").child(conversationId).child(messageId).setValue(msg).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(caller, caller.getString(R.string.en_error_msg), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Conversation conv = new Conversation(senderId, receiverId);
                    String messageId = Integer.toString(conv.getMessageCount());
                    database.getDatabaseController().child("conversations").child(senderId).child(conversationId).setValue(conv).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(caller, caller.getString(R.string.en_error_msg), Toast.LENGTH_LONG).show();
                        }
                    });
                    database.getDatabaseController().child("conversations").child(receiverId).child(conversationId).setValue(conv).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(caller, caller.getString(R.string.en_error_msg), Toast.LENGTH_LONG).show();
                        }
                    });
                    Message msg = new Message(senderId, receiverId, message, "1", conversationId);
                    database.getDatabaseController().child("messages").child(conversationId).child(messageId).setValue(msg).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(caller, caller.getString(R.string.en_error_msg), Toast.LENGTH_LONG).show();
                        }
                    });
                }
                loadConversation(activity, caller, conversationId);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(caller, caller.getString(R.string.en_error_msg), Toast.LENGTH_LONG).show();
            }
        });
    }

    protected void loadConversation(final ConversationActivity activity, final Context caller, String conversationId) {
        database.getDatabaseController().child("messages").child(conversationId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                ConversationController cont = new ConversationController(caller);
                ArrayList<Message> messages = new ArrayList<>();
                Iterable<DataSnapshot> messageChildren = snapshot.getChildren();
                for (DataSnapshot message : messageChildren) {
                    Message m = message.getValue(Message.class);
                    messages.add(m);
                }
                cont.updateConversationCallback(activity, caller, messages);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(caller, caller.getString(R.string.en_error_msg), Toast.LENGTH_LONG).show();
            }
        });
    }
}
