package uu.pss_group.f.codechat.view;


import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import uu.pss_group.f.codechat.R;
import uu.pss_group.f.codechat.domain.ConversationController;
import uu.pss_group.f.codechat.domain.Message;
import uu.pss_group.f.codechat.domain.MessagesManagement;
import uu.pss_group.f.codechat.domain.UserController;

public class ViewController {
    //Attributes
    private Context caller;

    //Constructor
    public ViewController(Context caller) {
        this.caller = caller;
    }

    //Register new user
    protected void signUpUser(String username, String email, String password, String passwordConf) {
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(caller, caller.getString(R.string.en_fill_username_msg), Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(caller, caller.getString(R.string.en_fill_email_msg), Toast.LENGTH_LONG).show();
            return;
        } else if (isNotAValidPassword(password)) {
            Toast.makeText(caller, caller.getString(R.string.en_wrong_pass_msg), Toast.LENGTH_LONG).show();
            return;
        } else if (!password.equals(passwordConf)) {
            Toast.makeText(caller, caller.getString(R.string.en_wrong_pass_match_msg), Toast.LENGTH_LONG).show();
            return;
        }
        UserController cont = new UserController(caller);
        cont.signUpUser(username, email, password);
    }

    //Authentication
    public void logInUser(String email, String password) {
        UserController cont = new UserController(caller);
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(caller, caller.getString(R.string.en_fill_email_msg), Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password) || password == null) {
            Toast.makeText(caller, caller.getString(R.string.en_fill_password_msg), Toast.LENGTH_LONG).show();
            return;
        }
        cont.logInUser(email, password);
    }

    public void logOutUser() {
        UserController cont = new UserController(caller);
        cont.logOutUser(LogInActivity.class);
    }

    public void startActivityIfUserLoggedIn(Class activity) {
        UserController cont = new UserController(caller);
        cont.startActivityIfUserLoggedIn(activity);
    }

    public String getActiveUserId() {
        UserController cont = new UserController(caller);
        return cont.getActiveUserId();
    }

    //Password security Checking
    private boolean isValidated(String psw){
        if (     !psw.matches(".*[A-Z].*") ||
                !psw.matches(".*[a-z].*") ||
                !psw.matches(".*\\d.*")   ||
                psw.length() < 10) return false;
        return true;
    }

    private boolean isNotAValidPassword(String password) {
        return TextUtils.isEmpty(password) || !(isValidated(password));
    }

    //Conversation
    protected void loadConversation(ConversationActivity activity, String conversationId) {
        ConversationController cont = new ConversationController(caller);
        cont.loadConversation(activity, conversationId);
    }

    protected void refreshConversation(ConversationActivity activity, ArrayList<Message> messages) {
        LinearLayout layout = new LinearLayout(caller);
        layout.setOrientation(LinearLayout.VERTICAL);
        for(int i=0; i<messages.size(); ++i) {
            TextView msg = new TextView(caller);
            msg.setText(messages.get(i).getText());
            msg.setTextSize(18);
            msg.setPadding(18, 9, 18, 9);
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            if (messages.get(i).getSenderId().equals(getActiveUserId())) {
                if (i > 0 && messages.get(i-1).getSenderId().equals(messages.get(i).getSenderId())) {
                    params.setMargins(144, 0, 36, 9);
                } else {
                    params.setMargins(144, 3, 36, 9);
                }
                msg.setLayoutParams(params);
                msg.setBackgroundResource(R.drawable.speech_bubble_blue);
                LinearLayout msgLayout = new LinearLayout(caller);
                msgLayout.setOrientation(LinearLayout.VERTICAL);
                msgLayout.setGravity(Gravity.RIGHT);
                msgLayout.addView(msg);
                layout.addView(msgLayout);
            } else {
                if (i > 0 && messages.get(i-1).getSenderId().equals(messages.get(i).getSenderId())) {
                    params.setMargins(30, 0, 144, 9);
                } else {
                    params.setMargins(30, 3, 144, 9);
                }
                msg.setLayoutParams(params);
                msg.setBackgroundResource(R.drawable.speech_bubble_orange);
                LinearLayout msgLayout = new LinearLayout(caller);
                msgLayout.setOrientation(LinearLayout.VERTICAL);
                msgLayout.setGravity(Gravity.LEFT);
                msgLayout.addView(msg);
                layout.addView(msgLayout);
            }
        }
        activity.updateConversation(layout);
    }

    protected void sendMessage(ConversationActivity activity, String conversationId, String message, String senderId, String receiverId) {
        ConversationController cont = new ConversationController(caller);
        cont.sendMessage(activity, conversationId, message, senderId, receiverId);
    }

    public void updateConversationCallback(ConversationActivity activity, ArrayList<Message> messages) {
        refreshConversation(activity, messages);
    }
}
