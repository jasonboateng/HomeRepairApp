package com.uottawa.shize.homerepairapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import static org.junit.Assert.*;


public class UserTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void username_isCorrect() {
        String actual = User.setUsername("Ivan");
        String expected = "Ivan";
        assertEquals(expected,actual);
    }

    public void password_isCorrect() {
        String actual = User.setPassword("Password");
        String expected = "Password";
        assertEquals(expected,actual);
    }

    public void usertype_isCorrect() {
        String actual = User.setUserType("Admin");
        String expected = "Admin";
        assertEquals(expected,actual);
    }
}