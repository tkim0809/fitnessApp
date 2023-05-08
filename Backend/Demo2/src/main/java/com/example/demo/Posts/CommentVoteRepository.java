package com.example.demo.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
public interface CommentVoteRepository extends JpaRepository<CommentVotes, Integer>{

    CommentVotes findByComment(Comments result);
}
