package com.example.demo.DietPage;

import com.example.demo.DietPage.Diet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DietRepository extends JpaRepository<Diet, Long> {
    List<Diet> findByDateAndUser_Id(String date, Long userId);
}
