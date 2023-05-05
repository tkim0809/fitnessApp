package com.example.demo.Communities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Communities")
@RestController
public class CommunitiesController {

    @Autowired
    CommunitiesRepository communitiesRepository;

    @Autowired
    CommunitiesUserRepository communitiesUserRepository;

    private final Logger logger = LoggerFactory.getLogger(CommunitiesController.class);

    //create a new community
    @RequestMapping(method = RequestMethod.POST, path = "/communities/new")
    public String saveCommunity(@ApiParam(value = "Community object to be saved", required = true) @RequestBody Communities communities) {
        communitiesRepository.save(communities);
        return "{\"message\":\"success\"}";
    }

    //get all communities
    @RequestMapping(method = RequestMethod.GET, path = "/communities")
    public List<Communities> getAllCommunities() {
        logger.info("Entered into Controller Layer");
        List<Communities> results = communitiesRepository.findAll();
        logger.info("Number of Communities Fetched:" + results.size());
        return results;
    }

    //get a community by id
    @RequestMapping(method = RequestMethod.GET, path = "/communities/{communityId}")
    public Optional<Communities> findCommunityById(@ApiParam(value = "Community ID", required = true) @PathVariable("communityId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Communities> results = communitiesRepository.findById(id);
        logger.info("Community Fetched:" + results.get().getCommunityName());
        return results;
    }

    //get all users in a community
    @RequestMapping(method = RequestMethod.GET, path = "/communities/{communityId}/users")
    public List<CommunityUsers> getAllUsersInCommunity(@ApiParam(value = "Community ID", required = true) @PathVariable("communityId") int id) {
        logger.info("Entered into Controller Layer");
        List<CommunityUsers> results = communitiesUserRepository.findByCommunityId(id);
        logger.info("Number of Users Fetched:" + results.size());
        return results;
    }

    //add a user to a community
    @RequestMapping(method = RequestMethod.POST, path = "/communities/{communityId}/users/new")
    public String saveUserToCommunity(@ApiParam(value = "Community ID", required = true) @PathVariable("communityId") int id, @ApiParam(value = "User object to be saved", required = true) @RequestBody CommunityUsers communityUsers) {
        communitiesUserRepository.save(communityUsers);
        return "{\"message\":\"success\"}";
    }

    //delete a user from a community
    @RequestMapping(method = RequestMethod.DELETE, path = "/communities/{communityId}/users/{userId}")
    public String deleteUserFromCommunity(@ApiParam(value = "Community ID", required = true) @PathVariable("communityId") int id, @ApiParam(value = "User ID", required = true) @PathVariable("userId") int userId) {
        communitiesUserRepository.deleteById(userId);
        return "{\"message\":\"success\"}";
    }

    //delete a community
    @RequestMapping(method = RequestMethod.DELETE, path = "/communities/{communityId}")
    public String deleteCommunity(@ApiParam(value = "Community ID", required = true) @PathVariable("communityId") int id) {
        communitiesRepository.deleteById(id);
        return "{\"message\":\"success\"}";
    }

    //get all communities a user is in
    @RequestMapping(method = RequestMethod.GET, path = "/users/{userId}/communities")
    public List<CommunityUsers> getAllCommunitiesUserIsIn(@ApiParam(value = "User ID", required = true) @PathVariable("userId") String userName) {
        logger.info("Entered into Controller Layer");
        List<CommunityUsers> results = communitiesUserRepository.findByUserId(userName);
        logger.info("Number of Communities Fetched:" + results.size());
        return results;
    }

}
