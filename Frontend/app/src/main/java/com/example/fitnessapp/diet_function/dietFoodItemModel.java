package com.example.fitnessapp.diet_function;

public class dietFoodItemModel {
    String foodName;
    String meal;
    String cal;

    public dietFoodItemModel(String foodName, String meal, String cal) {
        this.foodName = foodName;
        this.meal = meal;
        this.cal = cal;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getMeal() {
        return meal;
    }

    public String getCal() {
        return cal;
    }
}
