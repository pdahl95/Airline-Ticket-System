package edu.csumb.pdahl.project2.model;

public class User {
    private String userId;
    private String userName;
    private String password;

    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }



}
