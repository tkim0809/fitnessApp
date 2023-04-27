package com.example.demo.AddGym;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Other user fields

    @ManyToMany
    @JoinTable(
            name = "user_gym_like",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "gym_id")
    )
    private List<Gym> likedGyms;

    @ManyToMany
    @JoinTable(
            name = "user_gym_dislike",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "gym_id")
    )
    private List<Gym> dislikedGyms;

    // Constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Other getter and setter methods for user fields

    public List<Gym> getLikedGyms() {
        return likedGyms;
    }

    public void setLikedGyms(List<Gym> likedGyms) {
        this.likedGyms = likedGyms;
    }

    public List<Gym> getDislikedGyms() {
        return dislikedGyms;
    }

    public void setDislikedGyms(List<Gym> dislikedGyms) {
        this.dislikedGyms = dislikedGyms;
    }
}
