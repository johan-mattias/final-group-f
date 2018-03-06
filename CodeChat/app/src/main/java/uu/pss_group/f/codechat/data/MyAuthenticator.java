package uu.pss_group.f.codechat.data;


import com.google.firebase.auth.FirebaseAuth;

public class MyAuthenticator {
    //Attributes
    private FirebaseAuth firebaseAuth;

    //Constructor
    protected MyAuthenticator() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    //Getters
    public FirebaseAuth getAuthController() {return firebaseAuth;}
}
