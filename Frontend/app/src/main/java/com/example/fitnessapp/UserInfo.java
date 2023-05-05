package com.example.fitnessapp;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class is used to store user information as they login
 */
public class UserInfo {
    //test for user id 39
    static String userID = null;
    static String userEmail = null;
    static String date = null;
    static Boolean hasUpDatedDiet = false;
    static Boolean hasProfile = false;
    static Boolean hasSetDietGoal = false;

    /**
     * @return true if user has set diet goal.
     */
    public static Boolean getHasSetDietGoal() {
        return hasSetDietGoal;
    }


    public static void setHasSetDietGoal(Boolean hasSetDietGoal) {
        UserInfo.hasSetDietGoal = hasSetDietGoal;
    }

    public static String getUserIdFromLogin() {return LoginPage.userId;}

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

    /**
     * @return true is user has update meal intake today
     */
    public static Boolean getHasUpDatedDiet() {
        return hasUpDatedDiet;
    }

    public static void setHasUpDatedDiet(Boolean hasUpDatedDiet) {
        UserInfo.hasUpDatedDiet = hasUpDatedDiet;
        System.out.println("hasUpdateDiet:"+UserInfo.hasUpDatedDiet);
    }

    /**
     * @return true if user has set up profile
     */
    public static Boolean getHasProfile() {
        return hasProfile;
    }

    public static void setHasProfile(Boolean hasProfile) {
        UserInfo.hasProfile = hasProfile;
    }
}
