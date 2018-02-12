package uu.pss_group.f.codechat.view;


import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import uu.pss_group.f.codechat.R;
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
            Toast.makeText(caller, caller.getString(R.string.en_fill_username_msg), Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(caller, caller.getString(R.string.en_fill_email_msg), Toast.LENGTH_LONG).show();
            return;
        } else if (isNotAValidPassword(password)) {
            Toast.makeText(caller, caller.getString(R.string.en_wrong_pass_msg), Toast.LENGTH_LONG).show();
            return;
        } else if (!password.equals(passwordConf)) {
            Toast.makeText(caller, caller.getString(R.string.en_wrong_pass_match_msg), Toast.LENGTH_LONG).show();
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

    public void startActivityIfUserLoggedIn(Class activity) {
        UserController cont = new UserController(caller);
        cont.startActivityIfUserLoggedIn(activity);
    }

    //Password security Checking
    private boolean isValidated(String psw){
        if (     !psw.matches(".*[A-Z].*") ||
                !psw.matches(".*[a-z].*") ||
                !psw.matches(".*\\d.*")   ||
                psw.length() < 10) return false;
        return true;
    }

    private boolean isNotAValidPassword(String password) {
        return TextUtils.isEmpty(password) || !(isValidated(password));
    }
}
