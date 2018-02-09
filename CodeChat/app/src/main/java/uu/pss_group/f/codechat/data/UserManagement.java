package uu.pss_group.f.codechat.data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import uu.pss_group.f.codechat.domain.Profile;
import uu.pss_group.f.codechat.domain.UserController;

public class UserManagement {
    //Attributes
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myDatabase;

    //Constructor
    public UserManagement() {
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.myDatabase = FirebaseDatabase.getInstance().getReference();
    }

    protected void createNewUser(final Context caller, final String username, final String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(caller, "User successfully signed up", Toast.LENGTH_LONG).show();

                        //Create the profile and store it in the database
                        String userId = task.getResult().getUser().getUid();
                        DataController cont = new DataController(caller);
                        Profile profile = cont.createNewProfile(userId, username, email);
                        myDatabase.child("profiles").child(userId).setValue(profile).addOnFailureListener(new OnFailureListener() {
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

    protected boolean checkIfLoggedInUser() {
        return (firebaseAuth.getCurrentUser() != null);
    }

    protected String getCurrentUserId() {
        if (checkIfLoggedInUser()) {
            return firebaseAuth.getCurrentUser().getUid();
        } else {
            return null;
        }
    }

}
