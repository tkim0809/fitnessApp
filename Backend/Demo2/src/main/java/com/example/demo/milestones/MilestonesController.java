package com.example.demo.milestones;

import com.example.demo.StatPage.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.milestones.MilestonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class MilestonesController {
    @Autowired
    MilestonesRepository milestonesRepository;

    private final Logger logger = LoggerFactory.getLogger(com.example.demo.milestones.MilestonesController.class);

    @RequestMapping(method = RequestMethod.POST, path = "/Milestones/new")
    public String saveMilestone(/*@RequestBody Map<String, String> requestBody,*/ @RequestBody Milestones milestone) {
        milestonesRepository.save(milestone);
        return "{\"message\":\"success\"}";
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/Milestone/{milestoneId}/completed")
    public String updateMilestoneStatus(@PathVariable("milestoneId") int id) {
        Optional<Milestones> optionalMilestone = milestonesRepository.findById(id);
        if (optionalMilestone.isPresent()) {
            Milestones milestone = optionalMilestone.get();
            milestone.setCompleted(true);
            milestonesRepository.save(milestone);
            return "{\"message\":\"success\"}";
        } else {
            return "{\"message\":\"Milestone not found\"}";
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/AllMilestones/{userId}")
    public List<Milestones> getAllMilestones(@PathVariable("userId") int id) {
        logger.info("Entered into Controller Layer");
        List<Milestones> results = milestonesRepository.findAllByUserId(id);
        logger.info("Number of Milestones Fetched:" + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/Milestone/{milestoneId}")
    public Optional<Milestones> findMilestoneById(@PathVariable("milestoneId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Milestones> results = milestonesRepository.findById(id);
        return results;
    }

    //@DeleteMapping("/{id}")
    @RequestMapping(method = RequestMethod.DELETE, path = "Milestone/delete/{id}")
    public ResponseEntity<?> deleteMilestone(@PathVariable Integer id) {
        Optional<Milestones> milestone = milestonesRepository.findById(id);
        if(milestone.isPresent()) {
            milestonesRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }


}
