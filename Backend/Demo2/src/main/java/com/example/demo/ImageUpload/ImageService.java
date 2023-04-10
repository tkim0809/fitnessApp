package com.example.demo.ImageUpload;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
public class ImageService {


    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public Image saveImage(Long userId, MultipartFile file) throws IOException {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        image.setUser(user);

        return imageRepository.save(image);
    }



    public List<Image> findAllByUserId(Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return imageRepository.findAllByUser(user);
    }

    public void deleteImage(Long userId, Long imageId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Image image = imageRepository.findByIdAndUser(imageId, user)
                .orElseThrow(() -> new RuntimeException("Image not found for the specified user"));
        imageRepository.delete(image);
    }
}
