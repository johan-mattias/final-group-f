package uu.pss_group.f.codechat.domain;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import uu.pss_group.f.codechat.R;
import uu.pss_group.f.codechat.data.MyAuthenticator;
import uu.pss_group.f.codechat.data.MyDatabase;

public class UserManagement {
    //Attributes
    private MyAuthenticator auth;
    private MyDatabase database;

    //Constructors
    protected UserManagement(MyAuthenticator auth, MyDatabase database) {
        this.database = database;
        this.auth = auth;
    }

    //Register new users
    protected void createNewUser(final Context caller, final String username, final String email, String password) {
        auth.getAuthController().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(caller, R.string.en_sign_up_ok_msg, Toast.LENGTH_LONG).show();

                        //Create the profile and store it in the database
                        String userId = task.getResult().getUser().getUid();
                        Profile profile = new Profile(userId, username, email);
                        database.getDatabaseController().child("profiles").child(userId).setValue(profile).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(caller, "Something went wrong... Please try again", Toast.LENGTH_LONG).show();
                            }
                        });
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

    //Users log in
    protected void logInUser(final Context caller, String email, String password) {
        auth.getAuthController().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(caller, "Something went wrong... Please try again", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }

    protected void startActivityIfUserLoggedIn(final Context caller, final Class activity) {
        auth.getAuthController().addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (auth.getAuthController().getCurrentUser() != null) {
                    Intent intent = new Intent(caller, activity);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    caller.startActivity(intent);

                }
            }
        });
    }

}
