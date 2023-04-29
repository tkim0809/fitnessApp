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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(tags = "Friends")
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

    @ApiOperation(value = "Add a friend")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Friend added successfully"),
            @ApiResponse(code = 400, message = "Invalid input or friend already added")
    })

    @PostMapping("/{userId}/add")
    public String addFriend(@ApiParam(value = "Email of the friend to be added", required = true) @RequestBody Map<String, String> requestBody, @ApiParam(value = "User ID", required = true) @PathVariable Long userId) {
        String friendEmail = requestBody.get("email");

        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found with id: " + userId));
        if (user.getEmail().equals(friendEmail)) {
            return "{\"message\" : \"failed\"}";
        }
        if (friendsRepository.existsByEmailAndFriendId(user.getEmail(), userId)) {
            return "{\"message\" : \"failed\"}";
        }
        AppUser friend = appUserRepository.findByEmail(friendEmail)
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found with email: " + friendEmail));
        Friends newFriendship1 = new Friends(user, friend.getId()); // Current user to friend relationship
        Friends newFriendship2 = new Friends(friend, user.getId()); // Friend to current user relationship
        friendsRepository.save(newFriendship1);
        friendsRepository.save(newFriendship2);

        // Send a notification to the new friend
        webSocketServer.sendMessageToPArticularUser(friend.getId().toString(), "User with ID: " + userId + " added you as a friend.");

        return "{\"message\" : \"success\"}";
    }




    @ApiOperation(value = "Get a list of friend emails")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Friends list retrieved successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @GetMapping("/{userId}/friends")
    public ResponseEntity<List<String>> getFriends(@ApiParam(value = "User ID", required = true) @PathVariable Long userId)  {
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


