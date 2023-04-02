package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "calories")
    private String calories;

    @Column(name = "date")
    private String date;

    @Column(name = "meal")
    private String meal;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;





    public Diet() {
    }

    public Diet(String name, String calories, String date, String meal) {
        this.name = name;
        this.calories = calories;
        this.date = date;
        this.meal = meal;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return name;
    }

    public void setFoodName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Diet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", date=" + date +
                ", meal=" + meal +
                '}';
    }
}