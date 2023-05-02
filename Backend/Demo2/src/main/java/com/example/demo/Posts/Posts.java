package com.example.demo.Posts;

import com.example.demo.appuser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    //private int votes;

    public Posts(String title, String body, AppUser user) {
        this.title = title;
        this.body = body;
        this.user = user;
        //this.votes = 0;
    }

    // getter for title
    public String getTitle() {
        return title;
    }

    // setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // getter for body
    public String getBody() {
        return body;
    }

    // setter for body
    public void setBody(String body) {
        this.body = body;
    }

    // getter for user
    public AppUser getUser() {
        return user;
    }

    // setter for user
    public void setUser(AppUser user) {
        this.user = user;
    }

//    // getter for votes
//    public int getVotes() {
//        return votes;
//    }
//
//    // setter for votes
//    public void setVotes(int votes) {
//        this.votes = votes;
//    }

}
