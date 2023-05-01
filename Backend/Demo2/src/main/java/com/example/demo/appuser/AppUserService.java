package com.example.demo.appuser;

import com.example.demo.AddGym.Gym;
import com.example.demo.AddGym.GymRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final GymRepository gymRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists) {
            // TODO check of attributes are the same and
            // TODO if email not confirmed send confirmation email.
            throw new IllegalStateException("email already taken");
        }

        appUserRepository.save(appUser);

        return "User registered successfully!";
    }

    @Transactional
    public void updateUserProfile(String email, String firstName, String lastName) {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);

        appUserRepository.save(appUser);
    }

    public List<AppUser> getUsersForGym(Long gymId, String type) {
        Optional<Gym> gym = gymRepository.findById(gymId);

        if (gym.isPresent()) {
            if (type.equals("like")) {
                List<AppUser> likedByUsers = gym.get().getLikedByUsers();
                return likedByUsers;
            } else if (type.equals("dislike")) {
                List<AppUser> dislikedByUsers = gym.get().getDislikedByUsers();
                return dislikedByUsers;
            } else {
                throw new IllegalArgumentException("Invalid type");
            }
        } else {
            throw new EntityNotFoundException("Gym not found");
        }
    }

    public void likeGym(String email, Long gymId) {
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        Optional<Gym> gym = gymRepository.findById(gymId);

        if (user.isPresent() && gym.isPresent()) {
            AppUser appUser = user.get();
            Gym gymToUpdate = gym.get();

            gymToUpdate.getLikedByUsers().add(appUser);
            gymRepository.save(gymToUpdate);
        } else {
            throw new EntityNotFoundException("User or gym not found");
        }
    }

    public void dislikeGym(String email, Long gymId) {
        Optional<AppUser> user = appUserRepository.findByEmail(email);
        Optional<Gym> gym = gymRepository.findById(gymId);

        if (user.isPresent() && gym.isPresent()) {
            AppUser appUser = user.get();
            Gym gymToUpdate = gym.get();

            gymToUpdate.getDislikedByUsers().add(appUser);
            gymRepository.save(gymToUpdate);
        } else {
            throw new EntityNotFoundException("User or gym not found");
        }
    }
}