package com.example.demo.ProfilePage;



import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByUser_Id(Long userId);
}
