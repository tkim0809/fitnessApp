package com.example.demo.AddFriends;

import java.sql.SQLException;

import com.example.demo.StatPage.StatController;
import com.example.demo.StatPage.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;

import com.example.demo.AddFriends.Friends;
import com.example.demo.AddFriends.FriendsRepository;

//@RestController
//public class FriendsController {

//    @Autowired
//    private FriendsRepository friendsRepository;
//
//    private final Logger logger = LoggerFactory.getLogger(FriendsController.class);
//
//    public FriendsController(FriendsRepository FriendsRepository) {
//        this.friendsRepository = FriendsRepository;
//    }

//    @RequestMapping(method = RequestMethod.POST, path = "/Friends/new")
//    public String addFriend(String userEmail, String friendEmail) throws SQLException {
//        int userId = friendsRepository.getUserId(userEmail);
//        int friendId = friendsRepository.getFriendId(friendEmail);
//        friendsRepository.addFriendship(userId, friendId);
//        return "{\"message\":\"success\"}";
//    }

//    @RequestMapping(method = RequestMethod.POST, path = "/friends/new")
//    public String saveWorkout(@RequestBody Friends friends) {
//        friendsRepository.save(friends);
//        return "{\"message\":\"success\"}";
//    }

//}

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

    private final FriendsRepository friendsRepository;
    private final AppUserRepository appUserRepository;

    public FriendsController(FriendsRepository friendRepository, AppUserRepository appUserRepository) {
        this.friendsRepository = friendRepository;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addFriend(@RequestBody String friendEmail, @PathVariable Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found with id: " + userId));
        if (user.getEmail().equals(friendEmail)) {
            return ResponseEntity.badRequest().body("You cannot add yourself as a friend.");
        }
        if (friendsRepository.existsByEmailAndFriendId(user.getEmail(), userId)) {
            return ResponseEntity.badRequest().body("You are already friends with this user.");
        }
        AppUser friend = appUserRepository.findByEmail(friendEmail)
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found with email: " + friendEmail));
        Friends newFriendship = new Friends(user, friend.getId());
        friendsRepository.save(newFriendship);
        return ResponseEntity.ok().build();
    }
}
