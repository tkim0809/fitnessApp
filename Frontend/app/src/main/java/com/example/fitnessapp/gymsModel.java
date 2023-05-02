package com.example.fitnessapp;

public class gymsModel {

    String gymName;
    String gymDescription;
    String gymLocation;
    String gymPhoneNumber;
    String gymHours;


    public gymsModel(String gymName, String gymDescription, String gymLocation, String gymPhoneNumber, String gymHours) {
        this.gymName = gymName;
        this.gymDescription = gymDescription;
        this.gymLocation = gymLocation;
        this.gymPhoneNumber = gymPhoneNumber;
        this.gymHours = gymHours;

    }


    public String getGymName() {
        return gymName;
    }

    public String getGymDescription() {
        return gymDescription;
    }

    public String getGymLocation() {
        return gymLocation;
    }
    public String getGymPhoneNumber() {return gymPhoneNumber; }
    public String getGymHours() {return gymHours; };
}
