package com.example.fitnessapp;

/**
 * This class is used to set constructor for milestoneModel and getters methods that is used for recycler view.
 */
public class milestoneModel {

    String workoutName;
    String workoutWeights;
    String workoutReps;
    String workoutSets;

    public milestoneModel(String workoutName, String workoutWeights, String workoutReps, String workoutSets) {
        this.workoutName = workoutName;
        this.workoutWeights = workoutWeights;
        this.workoutReps = workoutReps;
        this.workoutSets = workoutSets;
    }


    public String getWorkoutName() {
        return workoutName;
    }

    public String getWorkoutWeights() {
        return workoutWeights;
    }

    public String getWorkoutReps() {
        return workoutReps;
    }

    public String getWorkoutSets() {
        return workoutSets;
    }
}
