package uu.pss_group.f.codechat.data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class MyAuth {
    //Attributes
    private FirebaseAuth firebaseAuth;

    //Constructor
    public MyAuth() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    protected void createNewUser(final Context caller, String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(caller, "User successfully signed up", Toast.LENGTH_LONG).show();
                    } else {
                        if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(caller, "This user is already registered", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(caller, "Something went wrong... Please try again", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
    }

    protected void loginUser(final Context caller, String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(caller, "Something went wrong... Please try again", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }

    protected String getCurrentUserId() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        return currentUser.getUid();
    }
}
