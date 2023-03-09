package com.example.demo.registration;

import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;


    private final AppUserService appUserService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @PutMapping("/{email}")
    public ResponseEntity<String> updateProfile(@PathVariable String email, @RequestBody UpdateProfileRequest request) {
        appUserService.updateUserProfile(
                email,
                request.getFirstName(),
                request.getLastName()

        );
        return ResponseEntity.status(HttpStatus.OK).body("Profile updated successfully");
    }


}