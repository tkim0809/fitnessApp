package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;
import com.example.demo.DietPage.DailyTarget;
import com.example.demo.DietPage.DietGoalRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DecimalFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;







import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Diet")
@RestController
@AllArgsConstructor
@RequestMapping()
public class DietController {
    @Autowired
    private final DietRepository dietRepository;
    private final AppUserRepository appUserRepository;
    private final DailyTargetRepository dailyTargetRepository;
    private final DietGoalRepository dietGoalRepository;

    @ApiOperation(value = "Add or update a diet goal for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Diet goal added/updated successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PostMapping("/dietgoal/{userId}")
    public DietGoal addDietGoal(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "Diet goal object", required = true)
            @RequestBody DietGoal newDietGoal
    ) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<DietGoal> existingDietGoal = dietGoalRepository.findByUser_Id(userId);

        if (existingDietGoal.isPresent()) {
            DietGoal dietGoal = existingDietGoal.get();
            dietGoal.setDietGoalValue(newDietGoal.getDietGoalValue());
            // Add any other fields that you want to allow for updates
            return dietGoalRepository.save(dietGoal);
        } else {
            newDietGoal.setUser(user);
            return dietGoalRepository.save(newDietGoal);
        }
    }
    @ApiOperation(value = "Update a diet goal for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Diet goal updated successfully"),
            @ApiResponse(code = 404, message = "Diet goal not found")
    })

    @PutMapping("/dietgoal/{userId}")
    public DietGoal updateDietGoal(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "Updated diet goal object", required = true)
            @RequestBody DietGoal updatedDietGoal
    ) {
        DietGoal dietGoal = dietGoalRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Diet goal not found"));

        // Update the diet goal fields as needed
        int updatedDietGoalValue = updatedDietGoal.getDietGoalValue();
        if (updatedDietGoalValue > 0) {
            dietGoal.setDietGoalValue(updatedDietGoalValue);
        }



        return dietGoalRepository.save(dietGoal);
    }


    @ApiOperation(value = "Get a diet goal for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Diet goal found"),
            @ApiResponse(code = 404, message = "Diet goal not found")
    })
    @GetMapping("/dietgoal/{userId}")
    public DietGoal getDietGoal(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long userId
    ) {
        return dietGoalRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Diet goal not found"));
    }

    @ApiOperation(value = "Get diet entries for the user by date")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Diet entries found"),
            @ApiResponse(code = 404, message = "User not found")
    })

    @GetMapping("/diet/{userId}/{date}")
    public List<Map<String, Object>> getDietByUserAndDate(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "Date in yyyy-MM-dd format", required = true)
            @PathVariable String date
    )  {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Diet> diets = dietRepository.findByUserAndDate(user, date);

        List<Map<String, Object>> response = new ArrayList<>();
        for (Diet diet : diets) {
            Map<String, Object> dietData = new HashMap<>();
            dietData.put("name", diet.getName());
            dietData.put("calories", diet.getCalories());
            dietData.put("meal", diet.getMeal());
            response.add(dietData);
        }

        return response;
    }

    @ApiOperation(value = "Add a new diet entry for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Diet entry added successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    @PostMapping("/diet/{userId}")
    public Diet addDiet(
            @ApiParam(value = "User ID", required = true)
            @PathVariable Long userId,
            @ApiParam(value = "Diet entry object", required = true)
            @RequestBody Diet diet
    ) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String dateString = diet.getDate();

        Optional<DailyTarget> dailyTargetOpt = dailyTargetRepository.findByDateAndUser_Id(dateString, userId);
        DailyTarget dailyTarget;

        if (dailyTargetOpt.isPresent()) {
            dailyTarget = dailyTargetOpt.get();
        } else {
            DietGoal dietGoal = dietGoalRepository.findByUser_Id(userId)
                    .orElseThrow(() -> new RuntimeException("Diet goal not found"));
            dailyTarget = new DailyTarget(dietGoal.getDietGoalValue(), dateString, user);
            dailyTargetRepository.save(dailyTarget);
        }

        DietGoal dietGoal = dietGoalRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Diet goal not found"));
        dailyTarget.setDietGoal(dietGoal);

        diet.setUser(user);

        return dietRepository.save(diet);
    }

    @ApiOperation(value = "Update a diet entry")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Diet entry updated successfully"),
            @ApiResponse(code = 404, message = "Diet entry not found")
    })
    @PutMapping("/diet/{id}")
    public Diet updateDiet(
            @ApiParam(value = "Diet entry ID", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Updated diet entry object", required = true)
            @RequestBody Diet updatedDiet
    ) {
        Diet diet = dietRepository.findById(id).orElseThrow(() -> new RuntimeException("Diet not found"));
        diet.setName(updatedDiet.getName());
        diet.setCalories(updatedDiet.getCalories());
        diet.setDate(updatedDiet.getDate());
        diet.setMeal(updatedDiet.getMeal());
        return dietRepository.save(diet);
    }

    @ApiOperation(value = "Delete a diet entry")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Diet entry deleted successfully"),
            @ApiResponse(code = 404, message = "Diet entry not found")
    })
    @DeleteMapping("/diet/{id}")
    public void deleteDiet(
            @ApiParam(value = "Diet entry ID", required = true)
            @PathVariable Long id
    ) {
        dietRepository.deleteById(id);
    }

    @ApiOperation(value = "Get diet summary by date for the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Diet summary found"),
            @ApiResponse(code = 404, message = "Daily target not found")
    })
    @GetMapping("/diet")
    public DietSummary getDietsByDate(
            @ApiParam(value = "Date in yyyy-MM-dd format", required = true)
            @RequestParam("date") String date,
            @ApiParam(value = "User ID", required = true)
            @RequestParam("userId") Long userId
    )  {
        List<Diet> diets = dietRepository.findByDateAndUser_Id(date, userId);
        int totalCalories = 0;
        DailyTarget dailyTarget = dailyTargetRepository.findByDateAndUser_Id(date, userId)
                .orElseThrow(() -> new RuntimeException("Daily target not found"));
        int targetDiet = dailyTarget.getDietGoalValue();

        for (Diet diet : diets) {
            totalCalories += Integer.parseInt(diet.getCalories());
        }

        double achievedPercentage = 0.0;
        if (targetDiet > 0) {
            achievedPercentage = (double) totalCalories / targetDiet * 100;
        }

        DecimalFormat df = new DecimalFormat("#.#");
        achievedPercentage = Double.parseDouble(df.format(achievedPercentage));


        return new DietSummary(targetDiet, totalCalories, achievedPercentage, diets);
    }


}
