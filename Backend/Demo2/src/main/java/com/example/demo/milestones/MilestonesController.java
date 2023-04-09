package com.example.demo.milestones;

import com.example.demo.StatPage.Stats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.milestones.MilestonesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(method = RequestMethod.GET, path = "/AllMilestones")
    public List<Milestones> getAllMilestones() {
        logger.info("Entered into Controller Layer");
        List<Milestones> results = milestonesRepository.findAll();
        logger.info("Number of Milestones Fetched:" + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/Milestone/{milestoneId}")
    public Optional<Milestones> findMilestoneById(@PathVariable("milestoneId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Milestones> results = milestonesRepository.findById(id);
        return results;
    }

//    @DeleteMapping(path = "/milestone/{id}")
//    String deleteMilestone(@PathVariable int id){
//
//        // Check if there is an object depending on user and then remove the dependency
//        Milestones milestone = milestonesRepository.findMilestoneById(id);
//        milestone.setMilestoneName(null);
//        milestone.setMilestoneSets(null);
//        milestone.setMilestoneReps(null);
//        milestone.setMilestoneWeight(null);
//        milestonesRepository.save(milestone);
//
//        // delete the laptop if the changes have not been reflected by the above statement
//        milestonesRepository.deleteById(id);
//        return "{\"message\":\"success\"}";
//    }
}
