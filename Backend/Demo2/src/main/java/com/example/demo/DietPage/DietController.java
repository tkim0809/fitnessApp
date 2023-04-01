package com.example.demo.DietPage;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/diet")
public class DietController {

    private final DietRepository dietRepository;
    private final AppUserService appUserService;

    @GetMapping("/{id}")
    public Diet getDiet(@PathVariable Long id) {
        return dietRepository.findById(id).orElseThrow(() -> new RuntimeException("Diet not found"));
    }

    @PostMapping
    public Diet addDiet(@RequestBody Diet diet) {
        AppUser user = appUserService.getCurrentUser();
        diet.setUser(user);
        return dietRepository.save(diet);
    }

    @PutMapping("/{id}")
    public Diet updateDiet(@PathVariable Long id, @RequestBody Diet updatedDiet) {
        Diet diet = dietRepository.findById(id).orElseThrow(() -> new RuntimeException("Diet not found"));
        diet.setFoodName(updatedDiet.getFoodName());
        diet.setCalories(updatedDiet.getCalories());
        diet.setDate(updatedDiet.getDate());
        diet.setMeal(updatedDiet.getMeal());
        return dietRepository.save(diet);
    }

    @DeleteMapping("/{id}")
    public void deleteDiet(@PathVariable Long id) {
        dietRepository.deleteById(id);
    }
}
