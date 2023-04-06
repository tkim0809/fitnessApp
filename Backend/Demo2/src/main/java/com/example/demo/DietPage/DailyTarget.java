package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;

import javax.persistence.*;

@Entity
public class DailyTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @OneToOne
    @JoinColumn(name = "diet_goal_id")
    private DietGoal dietGoal;

    @Column(name = "diet_goal")
    private int dietGoalValue;

    public DailyTarget() {
    }

    public DailyTarget(int dietGoalValue, String date, AppUser user) {
        this.date = date;
        this.user = user;
        this.dietGoalValue = dietGoalValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public DietGoal getDietGoal() {
        return dietGoal;
    }

    public void setDietGoal(DietGoal dietGoal) {
        this.dietGoal = dietGoal;
    }

    public int getDietGoalValue() {
        return dietGoalValue;
    }

    public void setDietGoalValue(int dietGoalValue) {
        this.dietGoalValue = dietGoalValue;
    }
}
