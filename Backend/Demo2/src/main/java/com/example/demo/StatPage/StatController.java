package com.example.demo.StatPage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.StatPage.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StatController {
    @Autowired
    StatRepository statsRepository;

    private final Logger logger = LoggerFactory.getLogger(StatController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/stats/new")
    public String saveWorkout(@RequestBody Stats stats) {
        statsRepository.save(stats);
        return "{\"message\":\"success\"}";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/Workouts")
    public List<Stats> getAllWorkouts() {
        logger.info("Entered into Controller Layer");
        List<Stats> results = statsRepository.findAll();
        logger.info("Number of Workouts Fetched:" + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/workouts/{workoutId}")
    public Optional<Stats> findOwnerById(@PathVariable("ownerId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Stats> results = statsRepository.findById(id);
        return results;
    }
}