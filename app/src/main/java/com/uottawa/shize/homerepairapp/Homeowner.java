package com.uottawa.shize.homerepairapp;

public class Homeowner extends User {

    public Homeowner() {
        // required empty constructor
    }

    public Homeowner(String username, String password, String userType) {
        super(username, password, userType);
    }
}
