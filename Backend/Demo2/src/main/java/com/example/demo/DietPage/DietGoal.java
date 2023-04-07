package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;

import javax.persistence.*;

@Entity
public class DietGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "diet_goal")
    private int dietGoalValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public DietGoal() {
    }

    public DietGoal(int dietGoalValue, AppUser user) {
        this.dietGoalValue = dietGoalValue;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDietGoalValue() {
        return dietGoalValue;
    }

    public void setDietGoalValue(int dietGoalValue) {
        this.dietGoalValue = dietGoalValue;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "DietGoal{" +
                "id=" + id +
                ", dietGoalValue=" + dietGoalValue +
                ", user=" + user +
                '}';
    }
}
