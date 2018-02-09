package uu.pss_group.f.codechat.data;


import android.content.Context;
import uu.pss_group.f.codechat.domain.Profile;

public class DataController {
    //Attributes
    private Context caller;

    //Constructor
    public DataController(Context caller) {this.caller = caller;}

    //User creation
    public String createNewUser(String email, String password) {
        MyAuth auth = new MyAuth();
        auth.createNewUser(caller, email, password);
        auth.loginUser(caller, email, password);
        return auth.getCurrentUserId();
    }

    public void createNewProfile(Profile profile) {
        MyDatabase database = new MyDatabase();
        database.storeProfile(caller, profile, profile.getUserId());
    }
}
