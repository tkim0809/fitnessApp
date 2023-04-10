package com.example.demo.ImageUpload;

import com.example.demo.ImageUpload.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImage(@PathVariable Long userId, @RequestParam("file") MultipartFile file) throws IOException {
        Image image = imageService.saveImage(userId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(image);
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImagesByUser(@PathVariable Long userId) {
        List<Image> images = imageService.findAllByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(images);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<String> deleteImage(@PathVariable Long userId, @PathVariable Long imageId) {
        imageService.deleteImage(userId, imageId);
        return ResponseEntity.status(HttpStatus.OK).body("Image deleted successfully");
    }

    // Other methods, if needed
}
