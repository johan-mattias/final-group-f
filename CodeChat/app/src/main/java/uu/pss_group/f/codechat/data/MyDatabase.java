package uu.pss_group.f.codechat.data;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyDatabase {
    //Attributes
    private DatabaseReference myDatabase;

    //Constructor
    protected MyDatabase() {
        this.myDatabase = FirebaseDatabase.getInstance().getReference();
    }

    //Getters
    public DatabaseReference getDatabaseController() {return myDatabase;}
}
