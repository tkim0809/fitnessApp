package com.example.fitnessapp;

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
