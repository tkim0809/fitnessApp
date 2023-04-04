package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



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

    @GetMapping("/diet/{id}")
    public Diet getDiet(@PathVariable Long id) {
        return dietRepository.findById(id).orElseThrow(() -> new RuntimeException("Diet not found"));
    }

    @PostMapping("/diet/{userId}")
    public Diet addDiet(@PathVariable Long userId, @RequestBody Diet diet) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        diet.setUser(user);
        return dietRepository.save(diet);
    }

    @PutMapping("/diet/{id}")
    public Diet updateDiet(@PathVariable Long id, @RequestBody Diet updatedDiet) {
        Diet diet = dietRepository.findById(id).orElseThrow(() -> new RuntimeException("Diet not found"));
        diet.setFoodName(updatedDiet.getFoodName());
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
    public List<Diet> getDietsByDate(@RequestParam("date") String date, @RequestParam("userId") Long userId) {
        List<Diet> diets = dietRepository.findByDateAndUser_Id(date, userId);
        int totalCalories = 0;
        int targetDiet = 0;
        for (Diet diet : diets) {
            totalCalories += Integer.parseInt(diet.getCalories());
            targetDiet = diet.getTargetDiet();
        }
        double achievedPercentage = 0.0;
        if (targetDiet > 0) {
            achievedPercentage = (double) totalCalories / targetDiet * 100;
        }
        for (Diet diet : diets) {
            diet.setTotalCalories(totalCalories);
            diet.setTargetDiet(targetDiet);
            diet.setAchievedPercentage();
        }

        return diets;
    }



    @GetMapping("/diet/test")
    public String testEndpoint() {
        return "Test successful";
    }
}
