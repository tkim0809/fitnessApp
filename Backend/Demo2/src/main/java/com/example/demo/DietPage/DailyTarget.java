package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DailyTarget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "target_diet")
    private int targetDiet;

    @Column(name = "date")
    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public DailyTarget() {
    }

    public DailyTarget(int targetDiet, String date, AppUser user) {
        this.targetDiet = targetDiet;
        this.date = date;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTargetDiet() {
        return targetDiet;
    }

    public void setTargetDiet(int targetDiet) {
        this.targetDiet = targetDiet;
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
}
