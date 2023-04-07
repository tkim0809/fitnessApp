package com.example.demo.Leaderboards;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Table;
import org.springframework.core.style.ToStringCreator;
import javax.persistence.*;

@Entity
public class Leaderboards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Integer id;

    @Column(name = "username")
    @NotFound(action = NotFoundAction.IGNORE)
    private String username;

    @Column(name = "pushups")
    @NotFound(action = NotFoundAction.IGNORE)
    private String pushups;

    public Leaderboards() {

    }

    public Leaderboards(String username, String pushups) {
        this.username = username;
        this.pushups = pushups;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getPushups() {
        return this.pushups;
    }

    public void setPushups(String pushups) {
        this.pushups = pushups;
    }


    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId())
                .append("username", this.getUsername())
                .append("pushups", this.getPushups()).toString();
    }
}