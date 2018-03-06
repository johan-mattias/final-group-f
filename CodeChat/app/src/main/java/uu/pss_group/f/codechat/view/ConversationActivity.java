package uu.pss_group.f.codechat.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import uu.pss_group.f.codechat.R;

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {
    //Attributes
    private ScrollView scroll;
    private ImageButton sendBtn;
    private EditText messageField;
    private String conversationId;
    private String senderId, receiverId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        scroll = findViewById(R.id.scroll);
        Bundle bundle = getIntent().getExtras();
        conversationId = bundle.getString("conversationId");
        ViewController cont = new ViewController(this);
        senderId = cont.getActiveUserId();
        if (conversationId.split("&&&")[0].equals(senderId)) {
            receiverId = conversationId.split("&&&")[1];
        } else {
            receiverId = conversationId.split("&&&")[0];
        }
        cont.loadConversation(this, conversationId);
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        messageField = findViewById(R.id.conversation_msg_field);
        messageField.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE | android.text.InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        sendBtn = findViewById(R.id.conversation_send_button);
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == sendBtn) {
            String msg = messageField.getText().toString().trim();
            if (!msg.isEmpty()) {
                ViewController cont = new ViewController(this);
                messageField.setText("");
                cont.sendMessage(this, conversationId, msg, senderId, receiverId);
            }
        }
    }

    protected void updateConversation(LinearLayout layout) {
        scroll.removeAllViews();
        scroll.addView(layout);
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }
}
