package com.uottawa.shize.homerepairapp;

public class Administrator extends User {

    public Administrator() {
        // required empty constructor
    }

    public Administrator(String username, String password, String userType) {
        super(username, password, userType);
    }
}
