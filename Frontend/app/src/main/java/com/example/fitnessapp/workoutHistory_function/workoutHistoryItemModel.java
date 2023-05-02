package com.example.fitnessapp.workoutHistory_function;

public class workoutHistoryItemModel {
    String workoutName;
    String sets;
    String reps;
    String weight;

    public workoutHistoryItemModel(String workoutName, String sets, String reps, String weight) {
        this.workoutName = workoutName;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getSets() {
        return sets;
    }

    public String getReps() {
        return reps;
    }

    public String getWeight() {
        return weight;
    }
}
