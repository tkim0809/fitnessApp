package com.example.demo.ProfilePage;



import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping("/{userId}")
    public Profile getProfile(@PathVariable Long userId) {
        return profileRepository.findByUser_Id(userId);
    }

    @PostMapping("/{userId}")
    public Profile createProfile(@PathVariable Long userId, @RequestBody Profile profile) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    @PutMapping("/{userId}")
    public Profile updateProfile(@PathVariable Long userId, @RequestBody Profile updatedProfile) {
        Profile profile = profileRepository.findByUser_Id(userId);

        if (updatedProfile.getGender() != null) {
            profile.setGender(updatedProfile.getGender());
        }
        if (updatedProfile.getAge() != null) {
            profile.setAge(updatedProfile.getAge());
        }
        if (updatedProfile.getWeight() != null) {
            profile.setWeight(updatedProfile.getWeight());
        }
        if (updatedProfile.getEmail() != null) {
            profile.setEmail(updatedProfile.getEmail());
        }

        return profileRepository.save(profile);
    }

}
