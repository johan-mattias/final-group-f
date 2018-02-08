package uu.pss_group.f.codechat;

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

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    //Attributes
    private Button loginButton, signupButton;
    private EditText mailField, passwordField;
    private ProgressDialog loadingD;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        loginButton = findViewById(R.id.login_loginButton);
        signupButton = findViewById(R.id.login_signupButton);
        mailField = findViewById(R.id.login_emailField);
        passwordField = findViewById(R.id.login_passwordField);
        loadingD = new ProgressDialog(this);
        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view == loginButton) {
            logInUser();
        } else if (view == signupButton) {
            Intent myIntent = new Intent(this, SignUpActivity.class);
            startActivity(myIntent);
        }
    }

    public void onStart() {
        super.onStart();
        //Check if user is already logged in and skip this step if so
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            startMainMenu();
        }
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
        loadingD.setMessage("Loging In...");
        loadingD.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startMainMenu();
                        } else {
                            Toast.makeText(getApplicationContext(), "Something went wrong... Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        loadingD.cancel();
    }

    private void startMainMenu() {
        Intent myIntent = new Intent(this, MainActivity.class);
        startActivity(myIntent);
    }
}
