package com.example.demo.Leaderboards;

import com.example.demo.Leaderboards.Leaderboards;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaderboardsRepository extends JpaRepository<Leaderboards, Integer> {


}