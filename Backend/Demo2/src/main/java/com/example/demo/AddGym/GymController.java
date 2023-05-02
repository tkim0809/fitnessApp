package com.example.demo.AddGym;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Set;




@RestController
@RequestMapping("/gyms")
public class GymController {

    @Autowired
    private GymRepository gymRepository;

    @Autowired
    private AppUserService appUserService;

    // GET all gyms
    @GetMapping("/")
    public List<Gym> getAllGyms() {
        return gymRepository.findAll();
    }

    // GET a single gym by ID
    @GetMapping("/{id}")
    public ResponseEntity<Gym> getGymById(@PathVariable(value = "id") Long gymId) {
        Optional<Gym> gym = gymRepository.findById(gymId);

        if (gym.isPresent()) {
            return ResponseEntity.ok(gym.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST a new gym
    @PostMapping("/addGym")
    public ResponseEntity<Gym> addGym(@RequestBody Gym gym) {
        // Save the gym to the database
        Gym savedGym = gymRepository.save(gym);

        // Return the saved gym in the response body
        return ResponseEntity.ok(savedGym);
    }

    // UPDATE an existing gym
    @PutMapping("/{id}")
    public ResponseEntity<Gym> updateGym(@PathVariable(value = "id") Long gymId, @RequestBody Gym gym) {
        Optional<Gym> gymToUpdate = gymRepository.findById(gymId);

        if (gymToUpdate.isPresent()) {
            // Update the gym's fields
            Gym updatedGym = gymToUpdate.get();
            updatedGym.setName(gym.getName());
            updatedGym.setDescription(gym.getDescription());
            updatedGym.setLocationUrl(gym.getLocationUrl());

            // Save the updated gym to the database
            Gym savedGym = gymRepository.save(updatedGym);

            // Return the updated gym in the response body
            return ResponseEntity.ok(savedGym);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE a gym
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGym(@PathVariable(value = "id") Long gymId) {
        Optional<Gym> gym = gymRepository.findById(gymId);

        if (gym.isPresent()) {
            // Delete the gym from the database
            gymRepository.delete(gym.get());

            // Return a success status code with no response body
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST a new like for a gym
// POST a new like for a gym
    @PostMapping("/{id}/like/{userId}")
    public ResponseEntity<String> addLikeForGym(@PathVariable(value = "id") Long gymId, @PathVariable(value = "userId") Long userId) {
        try {
            appUserService.likeGym(userId, gymId);
            return ResponseEntity.ok("Like added successfully!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // POST a new dislike for a gym
    @PostMapping("/{id}/dislike/{userId}")
    public ResponseEntity<String> addDislikeForGym(@PathVariable(value = "id") Long gymId, @PathVariable(value = "userId") Long userId) {
        try {
            appUserService.dislikeGym(userId, gymId);
            return ResponseEntity.ok("Dislike added successfully!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // GET emails of users who liked/disliked a gym
    @GetMapping("/{id}/users")
    public ResponseEntity<List<String>> getUsersForGym(@PathVariable(value = "id") Long gymId, @RequestParam(value = "type") String type) {
        Set<AppUser> users;

        try {
            users = appUserService.getUsersForGym(gymId, type);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        // Create a new list of emails from the AppUser objects
        List<String> userEmails = users.stream().map(AppUser::getEmail).collect(Collectors.toList());

        return ResponseEntity.ok(userEmails);
    }

}
