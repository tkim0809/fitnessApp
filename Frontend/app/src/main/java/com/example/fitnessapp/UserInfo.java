package com.example.fitnessapp;

public class UserInfo {
    static String userID = null;
    String userEmail = null;

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        UserInfo.date = date;
    }

    static String date = null;

    public static Boolean getUpDatedDiet() {
        return upDatedDiet;
    }

    public static void setUpDatedDiet(Boolean b) {
        upDatedDiet = b;
    }

    static Boolean upDatedDiet = false;

    public static String getUserID() {
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
