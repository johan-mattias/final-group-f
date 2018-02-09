package uu.pss_group.f.codechat.view;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import uu.pss_group.f.codechat.data.DataController;
import uu.pss_group.f.codechat.domain.UserController;

public class ViewController {
    //Attributes
    private Context caller;

    //Constructor
    public ViewController(Context caller) {
        this.caller = caller;
    }

    //Register new user
    protected void signUpUser(String username, String email, String password, String passwordConf) {
        if (TextUtils.isEmpty(username)) {
            displayToast(caller, "Please fill in the Username field");
            return;
        } else if (TextUtils.isEmpty(email)) {
            displayToast(caller, "Please fill in the Email field");
            return;
        } else if (isNotAValidPassword(password)) {
            displayToast(caller, "Password should contain at least one uppercase "+
                    "character, a lowercase character, a digit and should be 10 characters long!");
            return;
        } else if (!password.equals(passwordConf)) {
            displayToast(caller, "The passwords don't match");
            return;
        }
        UserController cont = new UserController(caller);
        cont.signUpUser(username, email, password);
    }

    //Authentication
    public void logInUser(String email, String password) {
        UserController cont = new UserController(caller);
        cont.logInUser(email, password);
    }

    public boolean checkIfLoggedInUser() {
        UserController cont = new UserController(caller);
        return cont.checkIfLoggedInUser();
    }

    //Password security Checking
    private boolean isValidated(String psw){
        if(     !psw.matches(".*[A-Z].*") ||
                !psw.matches(".*[a-z].*") ||
                !psw.matches(".*\\d.*")   ||
                psw.length() < 10) return false;
        return true;
    }

    private boolean isNotAValidPassword(String password) {
        return TextUtils.isEmpty(password) || !(isValidated(password));
    }

    //Print messages
    private void displayToast(Context caller, String msg){
        Toast.makeText(caller, msg, Toast.LENGTH_LONG).show();
    }

}
