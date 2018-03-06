package uu.pss_group.f.codechat.domain;


import android.content.Context;

import java.util.ArrayList;

import uu.pss_group.f.codechat.data.DataController;
import uu.pss_group.f.codechat.data.MyDatabase;
import uu.pss_group.f.codechat.view.ConversationActivity;
import uu.pss_group.f.codechat.view.ViewController;

public class ConversationController {

    //Attributes
    private Context caller;

    //Constructor
    public ConversationController(Context caller) {this.caller = caller;}

    //Server Controllers
    public MyDatabase getDatabaseReference() {
        DataController cont = new DataController();
        return cont.getDatabaseReference();
    }

    public void loadConversation(ConversationActivity activity, String conversationId) {
        MessagesManagement msgManager = new MessagesManagement(getDatabaseReference());
        msgManager.loadConversation(activity, caller, conversationId);
    }

    public void sendMessage(ConversationActivity activity, String conversationId, String message, String senderId, String receiverId) {
        MessagesManagement msgManager = new MessagesManagement(getDatabaseReference());
        msgManager.sendMessage(activity, caller, conversationId, message, senderId, receiverId);
    }

    public void updateConversationCallback(ConversationActivity activity, Context caller, ArrayList<Message> messages) {
        ViewController cont = new ViewController(caller);
        cont.updateConversationCallback(activity, messages);
    }
}
