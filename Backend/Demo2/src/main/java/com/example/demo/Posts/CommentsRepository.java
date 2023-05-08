package com.example.demo.Posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer>{
    List<Comments> findByPost(Posts post);
}
