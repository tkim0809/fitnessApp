package com.example.demo.AddGym;

import com.example.demo.appuser.AppUser;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String locationUrl;

    private int likes;
    private int dislikes;

    private String phoneNumber;
    private String hoursOfOperation;

    @ManyToMany(mappedBy = "likedGyms")
    private List<AppUser> likedByUsers;

    @ManyToMany(mappedBy = "dislikedGyms")
    private List<AppUser> dislikedByUsers;

    private Long user_id;

    // Constructor

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationUrl() {
        return locationUrl;
    }

    public void setLocationUrl(String locationUrl) {
        this.locationUrl = locationUrl;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public List<AppUser> getLikedByUsers() {
        return likedByUsers;
    }

    public void setLikedByUsers(List<AppUser> likedByUsers) {
        this.likedByUsers = likedByUsers;
    }

    public List<AppUser> getDislikedByUsers() {
        return dislikedByUsers;
    }

    public void setDislikedByUsers(List<AppUser> dislikedByUsers) {
        this.dislikedByUsers = dislikedByUsers;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
