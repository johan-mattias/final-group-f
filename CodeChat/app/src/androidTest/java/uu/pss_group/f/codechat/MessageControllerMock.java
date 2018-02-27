package uu.pss_group.f.codechat;

import android.util.Log;

import java.util.List;

import uu.pss_group.f.codechat.controllers.MessageController;
import uu.pss_group.f.codechat.demo.Message;

/**
 * Created by rasmus on 2018-02-26.
 */

public class MessageControllerMock extends MessageController {

    int counter = 0;
    List<Message> msgs;

    public int countConversations(){
        return counter;
    }

    public List<Message> getMsgs(){
        return msgs;
    }

    @Override
    public void updateView(List messages) {
        this.counter = messages.size();
        this.msgs = messages;
        Message m = (Message) messages.get(0);
        Log.d("yay","author of first message: "+m.getAuthor());
        Log.d("yay","Messages in MessageController: "+counter);
    }

}
