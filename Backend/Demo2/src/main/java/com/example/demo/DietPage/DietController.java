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







import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping()
public class DietController {
    @Autowired
    private final DietRepository dietRepository;
    private final AppUserRepository appUserRepository;
    private final DailyTargetRepository dailyTargetRepository;
    private final DietGoalRepository dietGoalRepository;

    @PostMapping("/dietgoal/{userId}")
    public DietGoal addDietGoal(@PathVariable Long userId, @RequestBody DietGoal newDietGoal) {
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


    @PutMapping("/dietgoal/{userId}")
    public DietGoal updateDietGoal(@PathVariable Long userId, @RequestBody DietGoal updatedDietGoal) {
        DietGoal dietGoal = dietGoalRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Diet goal not found"));

        // Update the diet goal fields as needed
        int updatedDietGoalValue = updatedDietGoal.getDietGoalValue();
        if (updatedDietGoalValue > 0) {
            dietGoal.setDietGoalValue(updatedDietGoalValue);
        }



        return dietGoalRepository.save(dietGoal);
    }



    @GetMapping("/dietgoal/{userId}")
    public DietGoal getDietGoal(@PathVariable Long userId) {
        return dietGoalRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Diet goal not found"));
    }


    @GetMapping("/diet/{userId}/{date}")
    public List<Map<String, Object>> getDietByUserAndDate(@PathVariable Long userId, @PathVariable String date) {
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


    @PostMapping("/diet/{userId}")
    public Diet addDiet(@PathVariable Long userId, @RequestBody Diet diet) {
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

    @PutMapping("/diet/{id}")
    public Diet updateDiet(@PathVariable Long id, @RequestBody Diet updatedDiet) {
        Diet diet = dietRepository.findById(id).orElseThrow(() -> new RuntimeException("Diet not found"));
        diet.setName(updatedDiet.getName());
        diet.setCalories(updatedDiet.getCalories());
        diet.setDate(updatedDiet.getDate());
        diet.setMeal(updatedDiet.getMeal());
        return dietRepository.save(diet);
    }

    @DeleteMapping("/diet/{id}")
    public void deleteDiet(@PathVariable Long id) {
        dietRepository.deleteById(id);
    }

    @GetMapping("/diet")
    public DietSummary getDietsByDate(@RequestParam("date") String date, @RequestParam("userId") Long userId) {
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
