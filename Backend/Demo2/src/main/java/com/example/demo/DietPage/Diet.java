package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String foodName;

    @Column(name = "calories")
    private String calories;

    @Column(name = "date")
    private String date;

    @Column(name = "meal")
    private String meal;

    @Column(name = "target_diet")
    private int targetDiet;

    @Column(name = "total_calories")
    private int totalCalories;

    @Column(name = "achieved_percentage")
    private double achievedPercentage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Diet() {
    }

    public Diet(String foodName, String calories, String date, String meal, int targetDiet) {
        this.foodName = foodName;
        this.calories = calories;
        this.date = date;
        this.meal = meal;
        this.targetDiet = targetDiet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
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

    public void setAchievedPercentage() {
        if (targetDiet != 0) {
            achievedPercentage = Math.round((double) totalCalories / targetDiet * 10000.0) / 100.0;
        } else {
            achievedPercentage = 0.0;
        }
    }

    @Override
    public String toString() {
        return "Diet{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", calories=" + calories +
                ", date=" + date +
                ", meal=" + meal +
                '}';
    }
}

