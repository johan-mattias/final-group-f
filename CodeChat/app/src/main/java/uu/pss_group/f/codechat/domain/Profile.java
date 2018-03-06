package uu.pss_group.f.codechat.domain;


public class Profile {
    //Attributes
    private String username, email, userId;

    //Constructor
    protected Profile(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    //Getters and Setters
    public String getUserId() {return this.userId;}

    public String getUsername() {return this.username;}

    public String getEmail() {return this.email;}

    protected void setUserId(String userId) {this.userId = userId;}

    protected void setUsername(String username) {this.username = username;}

    protected void setEmail(String email) {this.email = email;}
}
