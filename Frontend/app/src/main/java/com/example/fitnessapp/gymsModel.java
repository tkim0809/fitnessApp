package com.example.fitnessapp;

public class gymsModel {

    String gymName;
    String gymDescription;
    String gymLocation;


    public gymsModel(String gymName, String gymDescription, String gymLocation) {
        this.gymName = gymName;
        this.gymDescription = gymDescription;
        this.gymLocation = gymLocation;
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
}
