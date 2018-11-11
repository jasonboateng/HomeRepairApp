package com.uottawa.shize.homerepairapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;


public class UserTest {
    @Test
    public void username_isCorrect() {
        User u = new User();
        u.setUsername("Ivan");
        String actual = u.getUsername();
        String expected = "Ivan";
        assertEquals("Username check: ", expected, actual);
    }

    @Test
    public void password_isCorrect() {
        User u = new User();
        u.setPassword("Password");
        String actual = u.getPassword();
        String expected = "Password";
        assertEquals("Password check: ", expected, actual);
    }

    @Test
    public void userType_isCorrect() {
        User u = new User();
        u.setUserType("Admin");
        String actual = u.getUserType();
        String expected = "Admin";
        assertEquals("UserType check: ", expected, actual);
    }
}