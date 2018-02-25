package uu.pss_group.f.codechat.demo;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by rasmus on 2018-02-24.
 */

public class User extends HashMap<String,ArrayList<Conversation>> {

        private ArrayList<Conversation> conversations;

        //when you click "add conversation"
        //we get the hash from FB and add it to our list!
        public void addConversation(String key){
            this.get(key).add(new Conversation());

        }


}
