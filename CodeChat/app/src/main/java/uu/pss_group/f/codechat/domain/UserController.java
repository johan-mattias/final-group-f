package uu.pss_group.f.codechat.domain;


import android.content.Context;
import uu.pss_group.f.codechat.data.DataController;
import uu.pss_group.f.codechat.data.UserManagement;

public class UserController {
    //Attributes
    private Context caller;

    //Constructor
    public UserController(Context caller) {this.caller = caller;}

    //Register a new user
    public void signUpUser(String username, String email, String password) {
        DataController cont = new DataController(caller);
        cont.createNewUser(username, email, password);
    }

    public Profile createProfile(String userId, String username, String email) {
        Profile profile = new Profile(userId, username, email);
        return profile;
    }

    //Authentication
    public void logInUser(String email, String password) {
        DataController cont = new DataController(caller);
        cont.logInUser(email, password);
    }

    public boolean checkIfLoggedInUser() {
        DataController cont = new DataController(caller);
        return cont.checkIfLoggedInUser();
    }

}
