package uu.pss_group.f.codechat.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import uu.pss_group.f.codechat.R;

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {
    //Attributes
    private ScrollView scroll;
    private ImageButton sendBtn;
    private EditText messageField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        scroll = findViewById(R.id.scroll);

        ViewController cont = new ViewController(this, scroll);
        cont.loadConversation();


        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        messageField = findViewById(R.id.conversation_msg_field);
        sendBtn = findViewById(R.id.conversation_send_button);
        sendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == sendBtn) {
            ViewController cont = new ViewController(this);
            cont.sendMessage(messageField.getText().toString().trim());
        }

    }
}
