package uu.pss_group.f.codechat.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import uu.pss_group.f.codechat.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    //Attributes
    private Button signupButton;
    private EditText passwordField, passwordConfField, mailField, usernameField;
    private ProgressDialog loadingD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //Initialize
        signupButton = findViewById(R.id.signup_signupButton);
        mailField = findViewById(R.id.signup_emailField);
        passwordField = findViewById(R.id.signup_passwordField);
        passwordConfField = findViewById(R.id.signup_passwordConfField);
        usernameField = findViewById(R.id.signup_usernameField);
        loadingD = new ProgressDialog(this);
        signupButton.setOnClickListener(this);

        //Matching passwords in real time
        passwordConfField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String pass = passwordField.getText().toString();
                String passConf = passwordConfField.getText().toString();
                if (!pass.equals(passConf)) {
                    passwordConfField.setTextColor(Color.rgb(201,0,0));
                } else {
                    passwordConfField.setTextColor(Color.rgb(0,120,0));
                }
            }
        });
    }

    //Sign up button clicked listener
    @Override
    public void onClick(View view) {
        if (view == signupButton) {
            String username = usernameField.getText().toString().trim();
            String email = mailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String passwordConf = passwordConfField.getText().toString().trim();
            loadingD.setMessage("Registering User...");
            loadingD.show();
            final ViewController cont = new ViewController(getApplicationContext());
            cont.signUpUser(username, email, password, passwordConf);
            loadingD.cancel();
            FirebaseAuth.getInstance().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    if (cont.checkIfLoggedInUser()) {
                        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(myIntent);
                    }
                }
            });
        }
    }
}
