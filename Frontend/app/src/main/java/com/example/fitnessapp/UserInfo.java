package com.example.fitnessapp;

public class UserInfo {
    static String userID = null;
    static String userEmail = null;
    static String date = null;
    static Boolean hasUpDatedDiet = false;
    static Boolean hasProfile = false;
    static Boolean hasSetDietGoal = false;
    public static Boolean getHasSetDietGoal() {
        return hasSetDietGoal;
    }

    public static void setHasSetDietGoal(Boolean hasSetDietGoal) {
        UserInfo.hasSetDietGoal = hasSetDietGoal;
    }

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

    public static Boolean getHasUpDatedDiet() {
        return hasUpDatedDiet;
    }

    public static void setHasUpDatedDiet(Boolean hasUpDatedDiet) {
        UserInfo.hasUpDatedDiet = hasUpDatedDiet;
    }

    public static Boolean getHasProfile() {
        return hasProfile;
    }

    public static void setHasProfile(Boolean hasProfile) {
        UserInfo.hasProfile = hasProfile;
    }
}
