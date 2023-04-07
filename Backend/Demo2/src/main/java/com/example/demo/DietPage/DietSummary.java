package com.example.demo.DietPage;

import java.util.List;

public class DietSummary {
    private int targetDiet;
    private int totalCalories;
    private double achievedPercentage;
    private List<Diet> dietList;

    public DietSummary(int targetDiet, int totalCalories, double achievedPercentage, List<Diet> dietList) {
        this.targetDiet = targetDiet;
        this.totalCalories = totalCalories;
        this.achievedPercentage = achievedPercentage;
        this.dietList = dietList;
    }

    public int getTargetDiet() {
        return targetDiet;
    }

    public void setTargetDiet(int targetDiet) {
        this.targetDiet = targetDiet;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public double getAchievedPercentage() {
        return achievedPercentage;
    }

    public void setAchievedPercentage(double achievedPercentage) {
        this.achievedPercentage = achievedPercentage;
    }

    public List<Diet> getDietList() {
        return dietList;
    }

    public void setDietList(List<Diet> dietList) {
        this.dietList = dietList;
    }
}
