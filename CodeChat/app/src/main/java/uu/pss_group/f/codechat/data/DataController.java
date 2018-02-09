package uu.pss_group.f.codechat.data;


import android.content.Context;
import uu.pss_group.f.codechat.domain.Profile;
import uu.pss_group.f.codechat.domain.UserController;

public class DataController {
    //Attributes
    private Context caller;

    //Constructor
    public DataController(Context caller) {this.caller = caller;}

    //User creation
    public void createNewUser(String username, String email, String password) {
        UserManagement auth = new UserManagement();
        auth.createNewUser(caller, username, email, password);
        auth.loginUser(caller, email, password);
    }

    public Profile createNewProfile(String userId, String username, String email) {
        UserController cont = new UserController(caller);
        return cont.createProfile(userId, username, email);
    }

    //Authentication
    public void logInUser(String email, String password) {
        UserManagement auth = new UserManagement();
        auth.loginUser(caller, email, password);
    }

    public boolean checkIfLoggedInUser() {
        UserManagement auth = new UserManagement();
        return auth.checkIfLoggedInUser();
    }
}
