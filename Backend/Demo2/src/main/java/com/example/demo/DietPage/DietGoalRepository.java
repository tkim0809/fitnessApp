package com.example.demo.DietPage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DietGoalRepository extends JpaRepository<DietGoal, Long>{
    Optional<DietGoal> findByUser_Id(Long userId);
}
