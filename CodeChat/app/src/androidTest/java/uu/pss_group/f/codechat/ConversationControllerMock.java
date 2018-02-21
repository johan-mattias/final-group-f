package uu.pss_group.f.codechat;

import android.util.Log;

import java.util.List;

import uu.pss_group.f.codechat.controllers.ConversationController;
import uu.pss_group.f.codechat.domain.Conversation;

/**
 * Created by rasmus on 2018-02-22.
 */


public class ConversationControllerMock extends ConversationController {

    int counter = 0;
    List<Conversation> convs;

    public int countConversations(){
        return counter;
    }

    public List<Conversation> getConvs(){
        return convs;
    }

    @Override
    public void updateView(List conversations) {
        Log.d("yay","aaaaa"+counter);
        this.counter = conversations.size();
        this.convs = conversations;
        Log.d("yay","aaaaa"+counter);

    }

}