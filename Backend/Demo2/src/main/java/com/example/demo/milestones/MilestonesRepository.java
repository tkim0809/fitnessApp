package com.example.demo.milestones;

import com.example.demo.milestones.Milestones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MilestonesRepository extends JpaRepository<Milestones, Integer> {
    List<Milestones> findAllByUserId(Integer userId);

}
