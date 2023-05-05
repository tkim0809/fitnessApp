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
import com.example.demo.Notification.WebSocketServer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.json.JSONException;
import org.json.JSONObject;

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
        Friends newFriendship = new Friends(user, friend.getId());
        friendsRepository.save(newFriendship);

        // Send a notification to the new friend
        JSONObject notificationJson = new JSONObject();
        try {
            notificationJson.put("type", "friend_added");
            notificationJson.put("email", user.getEmail());
        } catch (JSONException e) {
            return "{\"message\" : \"failed\"}";
        }
        webSocketServer.sendMessageToParticularUser(friend.getId().toString(), notificationJson.toString());

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

    //remove friend
    @GetMapping("/{userId}/remove")
    public String removeFriend(@ApiParam(value = "User ID", required = true) @PathVariable Long userId, @ApiParam(value = "Email of the friend to be removed", required = true) @RequestBody Map<String, String> requestBody) {
        String friendEmail = requestBody.get("email");
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found with id: " + userId));
        AppUser friend = appUserRepository.findByEmail(friendEmail)
                .orElseThrow(() -> new org.springframework.security.core.userdetails.UsernameNotFoundException("User not found with email: " + friendEmail));
        friendsRepository.deleteByUserIdAndFriendId(user.getId(), friend.getId());
        friendsRepository.deleteByUserIdAndFriendId(friend.getId(), user.getId());
        return "{\"message\" : \"success\"}";
    }


}
