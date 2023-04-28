package com.example.fitnessapp;

/**
 * This class is used to set leaderboardModel and getters methods to use for recycler view
 */
public class leaderboardModel {

    String userName;
    String pushUps;


    public leaderboardModel(String userName, String pushUps) {
        this.userName = userName;
        this.pushUps = pushUps;
    }


    public String getUserName() {
        return userName;
    }

    public String getPushUps() {
        return pushUps;
    }
}
