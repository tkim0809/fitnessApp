package com.example.demo.Communities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CommunitiesRepository extends JpaRepository<Communities, Integer>{
}
