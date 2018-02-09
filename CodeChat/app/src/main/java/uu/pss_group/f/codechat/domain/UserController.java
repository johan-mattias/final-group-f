package uu.pss_group.f.codechat.domain;


import android.content.Context;
import uu.pss_group.f.codechat.data.DataController;

public class UserController {
    //Attributes
    private Context caller;

    //Constructor
    public UserController(Context caller) {this.caller = caller;}

    //Register a new user
    public void signUpUser(String username, String email, String password, String passwordConf) {
        DataController cont = new DataController(caller);
        String userId = cont.createNewUser(email, password);
        Profile newProfile = new Profile(userId, username, email);
        cont.createNewProfile(newProfile);
    }

}
