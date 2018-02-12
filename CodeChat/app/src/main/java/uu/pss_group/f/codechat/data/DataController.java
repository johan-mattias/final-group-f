package uu.pss_group.f.codechat.data;


public class DataController {
    //Get connections to server
    public MyAuthenticator getAuthReference() {
        MyAuthenticator myAuth = new MyAuthenticator();
        return myAuth;
    }

    public MyDatabase getDatabaseReference() {
        MyDatabase myDatabase = new MyDatabase();
        return myDatabase;
    }

}
