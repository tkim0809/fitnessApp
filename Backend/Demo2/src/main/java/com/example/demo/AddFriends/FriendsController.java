package com.example.demo.AddFriends;
import com.example.demo.AddFriends.Friends;

import com.example.demo.AddFriends.FriendsRepository;
import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.Notification.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

    private final FriendsRepository friendsRepository;
    private final AppUserRepository appUserRepository;

    @Autowired
    private WebSocketServer webSocketServer;

    public FriendsController(FriendsRepository friendRepository, AppUserRepository appUserRepository) {
        this.friendsRepository = friendRepository;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addFriend(@RequestBody Map<String, String> requestBody, @PathVariable Long userId) {
        String friendEmail = requestBody.get("email");

        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        if (user.getEmail().equals(friendEmail)) {
            return ResponseEntity.badRequest().body("You cannot add yourself as a friend.");
        }
        if (friendsRepository.existsByEmailAndFriendId(user.getEmail(), userId)) {
            return ResponseEntity.badRequest().body("You are already friends with this user.");
        }
        AppUser friend = appUserRepository.findByEmail(friendEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + friendEmail));
        Friends newFriendship = new Friends(user, friend.getId());
        friendsRepository.save(newFriendship);

        // Send a notification to the user who added the friend
        webSocketServer.sendMessageToPArticularUser(userId.toString(), "You added a new friend with ID: " + friend.getId());

        // Send a notification to the new friend
        webSocketServer.sendMessageToPArticularUser(friend.getId().toString(), "User with ID: " + userId + " added you as a friend.");

        return ResponseEntity.ok().build();
    }



    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<String>> getFriends(@PathVariable Long userId) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        List<Long> friendIds2 = friendsRepository.findFriendIdsByFriendId(user.getId());
        List<Long> friendIds1 = friendsRepository.findFriendIdsByUserId(user.getId());
        friendIds1.addAll(friendIds2);
        List<AppUser> friends = appUserRepository.findByIdIn(friendIds1);
        List<String> friendEmails = friends.stream().map(AppUser::getEmail).collect(Collectors.toList());
        return ResponseEntity.ok(friendEmails);
    }



}



