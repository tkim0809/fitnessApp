package com.example.demo.Posts;

import com.example.demo.StatPage.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Integer> {

}
