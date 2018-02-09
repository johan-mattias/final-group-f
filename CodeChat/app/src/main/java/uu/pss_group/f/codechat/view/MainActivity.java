package uu.pss_group.f.codechat.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uu.pss_group.f.codechat.R;

public class MainActivity extends AppCompatActivity {
    //Attributes
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        Toast.makeText(getApplicationContext(), "Logged in as:  " + currentUser.getEmail(), Toast.LENGTH_LONG).show();
    }
}
