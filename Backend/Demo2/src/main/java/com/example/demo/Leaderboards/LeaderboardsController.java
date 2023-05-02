package com.example.demo.Leaderboards;

import com.example.demo.Leaderboards.Leaderboards;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.Leaderboards.LeaderboardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Leaderboards")
@RestController
public class LeaderboardsController {
    @Autowired
    LeaderboardsRepository leaderboardsRepository;

    private final Logger logger = LoggerFactory.getLogger(com.example.demo.Leaderboards.LeaderboardsController.class);

    @ApiOperation(value = "Save a new leaderboard entry")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Leaderboard entry saved successfully")
    })
    @RequestMapping(method = RequestMethod.POST, path = "/leaderboards/new")
    public String saveWorkout(@ApiParam(value = "Leaderboard object containing leaderboard entry details", required = true) @RequestBody Leaderboards leaderboards) {
        leaderboardsRepository.save(leaderboards);
        return "{\"message\":\"success\"}";
    }

    @ApiOperation(value = "Get all users on the leaderboard")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Leaderboard users retrieved successfully")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/leaderboards")
    public List<Leaderboards> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<Leaderboards> results = leaderboardsRepository.findAll();
        results.sort((l1, l2) -> Integer.parseInt(l2.getPushups()) - Integer.parseInt(l1.getPushups()));
        logger.info("Number of People Fetched:" + results.size());
        return results;
    }

    @ApiOperation(value = "Find leaderboard entry by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Leaderboard entry retrieved successfully")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/leaderboards/{leaderboardsId}")
    public Optional<Leaderboards> findOwnerById(@ApiParam(value = "Leaderboard ID", required = true) @PathVariable("leaderboardsId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Leaderboards> results = leaderboardsRepository.findById(id);
        return results;
    }
}