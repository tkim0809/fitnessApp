package com.example.demo.Posts;

import com.example.demo.appuser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PostVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Posts post;

    private int vote;

    public PostVote(AppUser user, Posts post, int vote) {
        this.user = user;
        this.post = post;
        this.vote = vote;
    }

    public void upvote() {
        if (this.vote == 1) {
            this.vote = 0;
        } else {
            this.vote = 1;
        }
    }

    public void downvote() {
        if (this.vote == -1) {
            this.vote = 0;
        } else {
            this.vote = -1;
        }
    }

    public void removeVote() {
        this.vote = 0;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}
