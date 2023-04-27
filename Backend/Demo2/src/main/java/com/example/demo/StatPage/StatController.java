package com.example.demo.StatPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.StatPage.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Stats")
@RestController
public class StatController {
    @Autowired
    StatRepository statsRepository;

    private final Logger logger = LoggerFactory.getLogger(StatController.class);

    @ApiOperation(value = "Save a workout stat")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Workout stat saved successfully")
    })

    @RequestMapping(method = RequestMethod.POST, path = "/stats/new")
    public String saveWorkout(@ApiParam(value = "Workout stat object to be saved", required = true) @RequestBody Stats stats) {
        statsRepository.save(stats);
        return "{\"message\":\"success\"}";
    }

    @ApiOperation(value = "Get all workout stats")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Workout stats list retrieved successfully")
    })


    @RequestMapping(method = RequestMethod.GET, path = "/Workouts")
    public List<Stats> getAllWorkouts() {
        logger.info("Entered into Controller Layer");
        List<Stats> results = statsRepository.findAll();
        logger.info("Number of Workouts Fetched:" + results.size());
        return results;
    }

    @ApiOperation(value = "Find a workout stat by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Workout stat found"),
            @ApiResponse(code = 404, message = "Workout stat not found")
    })

    @RequestMapping(method = RequestMethod.GET, path = "/workouts/{workoutId}")
    public Optional<Stats> findOwnerById(@ApiParam(value = "Workout stat ID", required = true) @PathVariable("workoutId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Stats> results = statsRepository.findById(id);
        return results;
    }
}