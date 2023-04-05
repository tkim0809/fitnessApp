package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;
import com.example.demo.DietPage.DailyTarget;






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

    @GetMapping("/diet/{id}")
    public Diet getDiet(@PathVariable Long id) {
        return dietRepository.findById(id).orElseThrow(() -> new RuntimeException("Diet not found"));
    }

    @PostMapping("/diet/{userId}")
    public Diet addDiet(@PathVariable Long userId, @RequestBody Diet diet) {
        AppUser user = appUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String date = diet.getDate();
        LocalDate localDate = LocalDate.parse(date);
        String dateString = localDate.toString(); // get LocalDate in string format

        Optional<DailyTarget> dailyTargetOpt = dailyTargetRepository.findByDateAndUser_Id(localDate, userId);
        DailyTarget dailyTarget;

        if (dailyTargetOpt.isPresent()) {
            dailyTarget = dailyTargetOpt.get();
        } else {
            dailyTarget = new DailyTarget(0, dateString, user);
            dailyTargetRepository.save(dailyTarget);
        }

        diet.setUser(user);
        dailyTarget.setTargetDiet(dailyTarget.getTargetDiet());

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
    public DietSummary getDietsByDate(@RequestParam("date") String date, @RequestParam("userId") Long userId) {
        List<Diet> diets = dietRepository.findByDateAndUser_Id(date, userId);
        int totalCalories = 0;
        DailyTarget dailyTarget = dailyTargetRepository.findByDateAndUser_Id(LocalDate.parse(date), userId)
                .orElseThrow(() -> new RuntimeException("Daily target not found"));
        int targetDiet = dailyTarget.getTargetDiet();

        for (Diet diet : diets) {
            totalCalories += Integer.parseInt(diet.getCalories());
        }

        double achievedPercentage = 0.0;
        if (targetDiet > 0) {
            achievedPercentage = (double) totalCalories / targetDiet * 100;
        }

        return new DietSummary(targetDiet, totalCalories, achievedPercentage, diets);
    }


    @GetMapping("/diet/test")
    public String testEndpoint() {
        return "Test successful";
    }
}
