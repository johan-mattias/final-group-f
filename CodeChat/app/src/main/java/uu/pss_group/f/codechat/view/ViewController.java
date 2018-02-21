package uu.pss_group.f.codechat.view;


import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import uu.pss_group.f.codechat.R;
import uu.pss_group.f.codechat.domain.ConversationController;
import uu.pss_group.f.codechat.domain.Message;
import uu.pss_group.f.codechat.domain.UserController;

public class ViewController {
    //Attributes
    private Context caller;

    private ScrollView scroll;


    public ViewController(Context caller) {
        this.caller = caller;
    }

    //Constructor
    public ViewController(Context caller, ScrollView scroll) {
        this.caller = caller;
        this.scroll = scroll;
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
        cont.logInUser(email, password);
    }

    public void startActivityIfUserLoggedIn(Class activity) {
        UserController cont = new UserController(caller);
        cont.startActivityIfUserLoggedIn(activity);
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

    public void refresh(Message[] messages){
        LinearLayout layout = new LinearLayout(caller);

        layout.setOrientation(LinearLayout.VERTICAL);
// <<<<<<< HEAD
        ConversationController cont = new ConversationController();
        //Message[] messages= cont.loadConversation("Something", 0);
// =======


//>>>>>>> Started the implementation of the backend for Messages and Conversations.

        for(int i=0; i<messages.length; ++i) {
            TextView msg = new TextView(caller);
            msg.setText(messages[i].getText());
            msg.setTextSize(18);
            msg.setPadding(18, 9, 18, 9);
            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT, 1f);

// <<<<<<< HEAD
// =======
/*
            if (i > 0 && messages[i-1].getSenderId().equals(messages[i].getSenderId())) {
                params.setMargins(12, 0, 12, 12);
            } else {
                params.setMargins(12, 15, 12, 9);
            }

            msg.setLayoutParams(params);*/
// >>>>>>> Started the implementation of the backend for Messages and Conversations.
            if (messages[i].getSenderId().equals("123")) {
                if (i > 0 && messages[i-1].getSenderId().equals(messages[i].getSenderId())) {
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
                if (i > 0 && messages[i-1].getSenderId().equals(messages[i].getSenderId())) {
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

        scroll.removeAllViews();
        scroll.addView(layout);
    }

    //Conversation
    protected void loadConversation() {
        LinearLayout layout = new LinearLayout(caller);
        layout.setOrientation(LinearLayout.VERTICAL);
        ConversationController cont = new ConversationController(this);
        cont.loadConversation();

         //final Message[] messages = new Message[2];

         //messages[0] = new Message("sender","rec","teeex");
         // messages[1] = new Message("sender","rec","textttt");

         //refresh(messages);
    }

    protected void sendMessage(String message) {
        Toast.makeText(caller, "Sent: " + message, Toast.LENGTH_LONG).show();
    }
}
