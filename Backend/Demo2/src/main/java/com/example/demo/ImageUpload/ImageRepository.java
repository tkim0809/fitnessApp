package com.example.demo.ImageUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.appuser.AppUser;
import com.example.demo.ImageUpload.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByUser(AppUser user);
    Optional<Image> findByIdAndUser(Long id, AppUser user);
}