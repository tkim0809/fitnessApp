package com.example.demo.AddGym;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;



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
    @PostMapping("/{id}/like")
    public ResponseEntity<String> addLikeForGym(@PathVariable(value = "id") Long gymId, @RequestBody AppUser appUser) {
        Optional<Gym> gym = gymRepository.findById(gymId);

        if (gym.isPresent()) {
            Gym gymToUpdate = gym.get();
            gymToUpdate.getLikedByUsers().add(appUser);
            gymRepository.save(gymToUpdate);

            return ResponseEntity.ok("Like added successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST a new dislike for a gym
    @PostMapping("/{id}/dislike")
    public ResponseEntity<String> addDislikeForGym(@PathVariable(value = "id") Long gymId, @RequestBody AppUser appUser) {
        Optional<Gym> gym = gymRepository.findById(gymId);

        if (gym.isPresent()) {
            Gym gymToUpdate = gym.get();
            gymToUpdate.getDislikedByUsers().add(appUser);
            gymRepository.save(gymToUpdate);

            return ResponseEntity.ok("Dislike added successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // GET users who liked/disliked a gym
    @GetMapping("/{id}/users")
    public ResponseEntity<List<AppUser>> getUsersForGym(@PathVariable(value = "id") Long gymId, @RequestParam(value = "type") String type) {
        List<AppUser> users;

        try {
            users = appUserService.getUsersForGym(gymId, type);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(users);
    }

}
