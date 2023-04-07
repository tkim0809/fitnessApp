package com.example.fitnessapp;

public class UserInfo {
    String userID = null;
    String userEmail = null;

    public static Boolean getHasProfile() {
        return hasProfile;
    }

    public static void setHasProfile(Boolean hasProfile) {
        UserInfo.hasProfile = hasProfile;
    }

    static Boolean hasProfile = false;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
