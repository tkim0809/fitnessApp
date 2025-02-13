package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyTargetRepository extends JpaRepository<DailyTarget, Long> {
    Optional<DailyTarget> findByDateAndUser_Id(String date, Long userId);
}
