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

    private int votes;

    public Posts(String title, String body, AppUser user) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.votes = 0;
    }

    public void upvote() {
        this.votes++;
    }

    public void downvote() {
        this.votes--;
    }

    public void removeVote() {
        this.votes = 0;
    }

}
