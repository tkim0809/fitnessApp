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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Milestones")
@RestController
public class MilestonesController {
    @Autowired
    MilestonesRepository milestonesRepository;

    private final Logger logger = LoggerFactory.getLogger(com.example.demo.milestones.MilestonesController.class);

    @ApiOperation(value = "Save a new milestone")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Milestone saved successfully")
    })
    @RequestMapping(method = RequestMethod.POST, path = "/Milestones/new")
    public String saveMilestone(@ApiParam(value = "Milestone object containing milestone details", required = true) @RequestBody Milestones milestone) {
        milestonesRepository.save(milestone);
        return "{\"message\":\"success\"}";
    }

    @ApiOperation(value = "Update milestone status")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Milestone status updated successfully")
    })
    @RequestMapping(method = RequestMethod.PUT, path = "/Milestone/{milestoneId}/completed")
    public String updateMilestoneStatus(@ApiParam(value = "Milestone ID", required = true) @PathVariable("milestoneId") int id) {
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

    @ApiOperation(value = "Get all milestones for a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Milestones retrieved successfully")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/AllMilestones/{userId}")
    public List<Milestones> getAllMilestones(@ApiParam(value = "User ID", required = true) @PathVariable("userId") int id) {
        logger.info("Entered into Controller Layer");
        List<Milestones> results = milestonesRepository.findAllByUserId(id);
        logger.info("Number of Milestones Fetched:" + results.size());
        return results;
    }

    @ApiOperation(value = "Find milestone by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Milestone retrieved successfully")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/Milestone/{milestoneId}")
    public Optional<Milestones> findMilestoneById(@ApiParam(value = "Milestone ID", required = true) @PathVariable("milestoneId") int id) {
        logger.info("Entered into Controller Layer");
        Optional<Milestones> results = milestonesRepository.findById(id);
        return results;
    }

    @ApiOperation(value = "Delete a milestone by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Milestone deleted successfully"),
            @ApiResponse(code = 404, message = "Milestone not found")
    })
    @RequestMapping(method = RequestMethod.DELETE, path = "Milestone/delete/{id}")
    public ResponseEntity<?> deleteMilestone(@ApiParam(value = "Milestone ID", required = true) @PathVariable Integer id) {
        Optional<Milestones> milestone = milestonesRepository.findById(id);
        if (milestone.isPresent()) {
            milestonesRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}