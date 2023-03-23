package com.example.demo.DietPage;

import com.example.demo.DietPage.Diet;
import com.example.demo.DietPage.DietRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DietController {

    @Autowired
    private DietRepository dietRepository;

    @GetMapping("/diets")
    public List<Diet> getAllDiets() {
        return dietRepository.findAll();
    }

    @GetMapping("/diets/{id}")
    public Diet getDietById(@PathVariable(value = "id") Long id) {
        return dietRepository.findById(id).orElse(null);
    }

    @PostMapping("/diets")
    public Diet createDiet(@RequestBody Diet diet) {
        return dietRepository.save(diet);
    }

    @PutMapping("/diets/{id}")
    public Diet updateDiet(@PathVariable(value = "id") Long id, @RequestBody Diet dietDetails) {
        Diet diet = dietRepository.findById(id).orElse(null);
        if (diet == null) {
            return null;
        }
        diet.setFoodName(dietDetails.getFoodName());
        diet.setCalories(dietDetails.getCalories());
        diet.setDate(dietDetails.getDate());
        diet.setTime(dietDetails.getTime());
        return dietRepository.save(diet);
    }

    @DeleteMapping("/diets/{id}")
    public void deleteDiet(@PathVariable(value = "id") Long id) {
        dietRepository.deleteById(id);
    }
}
