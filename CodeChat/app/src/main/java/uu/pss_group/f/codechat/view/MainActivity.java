package uu.pss_group.f.codechat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uu.pss_group.f.codechat.R;
import uu.pss_group.f.codechat.domain.Conversation;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    //Attributes
    private FirebaseAuth firebaseAuth;
    private Button conversationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        Toast.makeText(getApplicationContext(), "Logged in as:  " + currentUser.getEmail(), Toast.LENGTH_LONG).show();

        conversationButton = findViewById(R.id.conversation_button);
        conversationButton.setOnClickListener(this);

    }

    public void onClick(View view) {
        if (view == conversationButton) {
            Intent intent = new Intent(getApplicationContext(), ConversationActivity.class);
            startActivity(intent);
        }
    }
}
