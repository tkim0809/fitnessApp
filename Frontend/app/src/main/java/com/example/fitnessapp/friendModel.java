package com.example.fitnessapp;

/**
 * This class is used to construct friendModel and getters method
 */
public class friendModel {

    String email;
    String userName;

    public friendModel(String email, String userName) {
        this.email = email;
        this.userName = userName;

    }


    public String getEmail() {
        return email;
    }
    public String getUserName() {

        return userName;

    }
}
