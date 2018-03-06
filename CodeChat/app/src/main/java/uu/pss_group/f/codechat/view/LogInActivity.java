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
        loadingD.setMessage("Logging In...");
        loadingD.show();
        ViewController cont = new ViewController(getApplicationContext());
        cont.logInUser(email, password);
        loadingD.cancel();
        cont.startActivityIfUserLoggedIn(MainActivity.class);
    }
}
