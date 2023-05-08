package com.example.demo.Posts;

import com.example.demo.appuser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Comments {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String body;

        @ManyToOne(fetch = FetchType.LAZY)
        private AppUser user;

        //private int votes;

        @ManyToOne(fetch = FetchType.LAZY)
        private Posts post;

        public Comments(String body, Posts post, AppUser user) {
            this.body = body;
            this.post = post;
            this.user = user;
            //this.votes = 0;
        }

//        public void upvote() {
//            this.votes++;
//        }
//
//        public void downvote() {
//            this.votes--;
//        }
//
//        public void removeVote() {
//            this.votes = 0;
//        }
//
//        public String setComment(String body) {
//            return this.body = body;
//        }
//
//        public String getComment() {
//            return body;
//        }

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

        // getter for post
        public Posts getPost() {
            return post;
        }

        // setter for post
        public void setPost(Posts post) {
            this.post = post;
        }
}
