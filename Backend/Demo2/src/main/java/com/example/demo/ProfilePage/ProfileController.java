package com.example.demo.ProfilePage;



import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Profile")
@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @ApiOperation(value = "Get user profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile retrieved successfully")
    })
    @GetMapping("/{userId}")
    public Profile getProfile(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long userId
    ) {
        return profileRepository.findByUser_Id(userId);
    }

    @ApiOperation(value = "Create user profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile created successfully")
    })
    @PostMapping("/{userId}")
    public Profile createProfile(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "Profile object containing profile details", required = true)
            @RequestBody Profile profile
    ) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        profile.setUser(user);
        return profileRepository.save(profile);
    }

    @ApiOperation(value = "Update user profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profile updated successfully")
    })
    @PutMapping("/{userId}")
    public Profile updateProfile(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "Updated profile object containing new profile details", required = true)
            @RequestBody Profile updatedProfile
    ) {
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

        if (updatedProfile.getUserName() != null) {
            profile.setUserName((updatedProfile.getUserName()));
        }

        return profileRepository.save(profile);
    }

}