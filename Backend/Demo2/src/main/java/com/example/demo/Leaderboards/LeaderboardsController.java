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

@RestController
public class LeaderboardsController {
    @Autowired
    LeaderboardsRepository leaderboardsRepository;

    private final Logger logger = LoggerFactory.getLogger(com.example.demo.Leaderboards.LeaderboardsController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/leaderboards/new")
    public String saveWorkout(@RequestBody Map<String, String> requestBody, @RequestBody Leaderboards leaderboards) {
        leaderboardsRepository.save(leaderboards);
        return "{\"message\":\"success\"}";
    }


    @RequestMapping(method = RequestMethod.GET, path = "/leaderboards")
    public List<Leaderboards> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<Leaderboards> results = leaderboardsRepository.findAll();
        //results.sort();
        //results.sort(Comparator.comparingInt(l -> Integer.parseInt(l.getPushups())));
        results.sort((l1, l2) -> Integer.parseInt(l2.getPushups()) - Integer.parseInt(l1.getPushups()));
        logger.info("Number of People Fetched:" + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/leaderboards/{leaderboardsId}")
    public Optional<Leaderboards> findOwnerById(@PathVariable("leaderboardsId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Leaderboards> results = leaderboardsRepository.findById(id);
        return results;
    }
}