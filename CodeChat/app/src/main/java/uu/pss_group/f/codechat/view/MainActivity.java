package uu.pss_group.f.codechat.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import uu.pss_group.f.codechat.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    //Attributes
    private FirebaseAuth firebaseAuth;
    private Button conversationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        Toast.makeText(getApplicationContext(), "Logged in as:  " + currentUser.getEmail(), Toast.LENGTH_LONG).show();

        conversationButton = findViewById(R.id.conversation_button);
        conversationButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick(View view) {
        if (view == conversationButton) {
            Intent intent = new Intent(getApplicationContext(), ConversationActivity.class);
            intent.putExtra("conversationId", "oZzAxrK6mwUtsbmLkLyMupz70mC2&&&oAc52Dzy66VFmTSIFCTta7SGqjr2");
            startActivity(intent);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_log_out) {
            ViewController cont = new ViewController(getApplicationContext());
            cont.logOutUser();
            return true;
        }
        return false;
    }
}
