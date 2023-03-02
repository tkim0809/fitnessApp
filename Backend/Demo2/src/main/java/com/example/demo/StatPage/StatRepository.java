package com.example.demo.StatPage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StatRepository extends JpaRepository<Stats, Integer> {

}
