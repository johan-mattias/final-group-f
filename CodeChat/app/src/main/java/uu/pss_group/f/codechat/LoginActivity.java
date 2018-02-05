package uu.pss_group.f.codechat;

import android.app.ProgressDialog;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    //Attributes
    private Button signupButton;
    private EditText mailField, passwordField;
    private ProgressDialog loadingD;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signupButton = (Button) findViewById(R.id.signupButton);
        mailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        loadingD = new ProgressDialog(this);
        signupButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view == signupButton) {
            SignUpUser();
        }
    }

    private String passwordCheck(String psw){
        if(     !psw.matches(".*[A-Z].*") ||
                !psw.matches(".*[a-z].*") ||
                !psw.matches(".*\\d.*")   ||
                psw.length() < 10) return null;
        return psw;
    }

    private void SignUpUser() {
        String email = mailField.getText().toString().trim();
        String password = passwordCheck(passwordField.getText().toString().trim());

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please fill in the Email field", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password) || password == null) {
            Toast.makeText(getApplicationContext(), "Password should contain at least one uppercase"+
                    "character, a lowercase character, a digit and should be 10 characters long!", Toast.LENGTH_LONG).show();
            return;
        }
        loadingD.setMessage("Registering User...");
        loadingD.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "User successfully signed up", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Something went wrong... Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
        });
        loadingD.cancel();
    }
}
