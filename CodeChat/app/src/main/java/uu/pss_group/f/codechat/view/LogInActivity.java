package uu.pss_group.f.codechat.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import uu.pss_group.f.codechat.R;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    //Attributes
    private Button loginButton, signUpButton;
    private EditText mailField, passwordField;
    private ProgressDialog loadingD;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginButton = findViewById(R.id.login_loginButton);
        signUpButton = findViewById(R.id.login_signupButton);
        mailField = findViewById(R.id.login_emailField);
        passwordField = findViewById(R.id.login_passwordField);
        loadingD = new ProgressDialog(this);
        loginButton.setOnClickListener(this);
        signUpButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            logInUser();
        } else if (view == signUpButton) {
            Intent myIntent = new Intent(this, SignUpActivity.class);
            startActivity(myIntent);
        }
    }

    public void onStart() {
        super.onStart();
        ViewController cont = new ViewController(getApplicationContext());
        cont.startActivityIfUserLoggedIn(MainActivity.class);
    }

    private void logInUser() {
        String email = mailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please fill in the Email field", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password) || password == null) {
            Toast.makeText(getApplicationContext(), "Please fill in the Password field", Toast.LENGTH_LONG).show();
            return;
        }
        loadingD.setMessage("Logging In...");
        loadingD.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(myIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Something went wrong... Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        loadingD.cancel();
    }
}
