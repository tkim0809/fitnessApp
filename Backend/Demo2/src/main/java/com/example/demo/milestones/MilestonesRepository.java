package com.example.demo.milestones;

import com.example.demo.StatPage.Stats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MilestonesRepository extends JpaRepository<Stats, Integer> {

}
