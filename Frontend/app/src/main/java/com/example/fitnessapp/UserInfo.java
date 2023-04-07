package com.example.fitnessapp;

public class UserInfo {
    static String userID = null;
    static String userEmail = null;
    static String date = null;
    static Boolean upDatedDiet = false;
    static Boolean hasProfile = false;

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        UserInfo.userID = userID;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static void setUserEmail(String userEmail) {
        UserInfo.userEmail = userEmail;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        UserInfo.date = date;
    }

    public static Boolean getUpDatedDiet() {
        return upDatedDiet;
    }

    public static void setUpDatedDiet(Boolean upDatedDiet) {
        UserInfo.upDatedDiet = upDatedDiet;
    }

    public static Boolean getHasProfile() {
        return hasProfile;
    }

    public static void setHasProfile(Boolean hasProfile) {
        UserInfo.hasProfile = hasProfile;
    }
}
