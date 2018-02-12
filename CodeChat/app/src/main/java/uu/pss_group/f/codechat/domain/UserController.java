package uu.pss_group.f.codechat.domain;


import android.content.Context;
import uu.pss_group.f.codechat.data.DataController;
import uu.pss_group.f.codechat.data.MyAuthenticator;
import uu.pss_group.f.codechat.data.MyDatabase;

public class UserController {
    //Attributes
    private Context caller;

    //Constructor
    public UserController(Context caller) {this.caller = caller;}

    //Server Controllers
    public MyDatabase getDatabaseReference() {
        DataController cont = new DataController();
        return cont.getDatabaseReference();
    }

    public MyAuthenticator getAuthReference() {
        DataController cont = new DataController();
        return cont.getAuthReference();
    }

    //Register a new user
    public void signUpUser(String username, String email, String password) {
        UserManagement userManager = new UserManagement(getAuthReference(), getDatabaseReference());
        userManager.createNewUser(caller, username, email, password);
        userManager.logInUser(caller, email, password);
    }

    //Authentication
    public void logInUser(String email, String password) {
        UserManagement userManager = new UserManagement(getAuthReference(), getDatabaseReference());
        userManager.logInUser(caller, email, password);
    }

    public void startActivityIfUserLoggedIn(Class activity) {
        UserManagement userManager = new UserManagement(getAuthReference(), getDatabaseReference());
        userManager.startActivityIfUserLoggedIn(caller, activity);
    }

}
