package uu.pss_group.f.codechat.data;


import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDatabase {
    //Attributes
    private DatabaseReference myDatabase;

    //Constructor
    protected MyDatabase() {
        this.myDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //Getters and Setters
    protected DatabaseReference getMyDatabase() {return myDatabase;}

    //Storing methods
    protected void storeProfile(final Context caller, Object profile, String userId) {
        myDatabase.child("profiles").child(userId).setValue(profile).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(caller, "Something went wrong... Please try again", Toast.LENGTH_LONG).show();
            }
        });
    }
}
