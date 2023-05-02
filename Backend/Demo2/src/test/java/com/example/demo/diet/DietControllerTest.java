package com.example.demo.diet;

import com.example.demo.DemoApplication;
import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.DietPage.DailyTarget;
import com.example.demo.DietPage.DailyTargetRepository;
import com.example.demo.DietPage.Diet;
import com.example.demo.DietPage.DietGoal;
import com.example.demo.DietPage.DietGoalRepository;
import com.example.demo.DietPage.DietRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@AutoConfigureWebTestClient
public class DietControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DietGoalRepository dietGoalRepository;

    @Autowired
    private DailyTargetRepository dailyTargetRepository;

    @Autowired
    private DietRepository dietRepository;

    private AppUser testUser;
    private DietGoal testDietGoal;
    private DailyTarget testDailyTarget;
    private Diet testDiet;

    @BeforeEach
    public void setUp() {
        // Prepare test data for AppUser
        testUser = new AppUser();
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("password");
        appUserRepository.save(testUser);

        // Prepare test data for DietGoal
        testDietGoal = new DietGoal(2000, testUser);
        dietGoalRepository.save(testDietGoal);

        // Prepare test data for DailyTarget
        testDailyTarget = new DailyTarget(testDietGoal.getDietGoalValue(), "2023-05-02", testUser);
        dailyTargetRepository.save(testDailyTarget);

        // Prepare test data for Diet
        testDiet = new Diet();
        testDiet.setName("Pasta");
        testDiet.setCalories("500");
        testDiet.setDate("2023-05-02");
        testDiet.setMeal("Lunch");
        testDiet.setUser(testUser);
        dietRepository.save(testDiet);
    }

    @Test
    public void testAddDietGoal() {
        String dietGoalJson = "{ \"dietGoalValue\": 2000 }";

        webTestClient.post()
                .uri("/dietgoal/" + testUser.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dietGoalJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.dietGoalValue").isEqualTo(2000);
    }

    @Test
    public void testUpdateDietGoal() {
        String updatedDietGoalJson = "{ \"dietGoalValue\": 2500 }";

        webTestClient.put()
                .uri("/dietgoal/" + testUser.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedDietGoalJson)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.dietGoalValue").isEqualTo(2500);
    }

    @Test
    public void testGetDietByUserAndDate() {
        webTestClient.get()
                .uri("/diet/" + testUser.getId() + "/2023-05-02")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Pasta")
                .jsonPath("$[0].calories").isEqualTo("500")
                .jsonPath("$[0].meal").isEqualTo("Lunch");
    }

    @Test
    public void testUpdateDiet() {
        Diet updatedDiet = new Diet();
        updatedDiet.setName("Pizza");
        updatedDiet.setCalories("700");
        updatedDiet.setDate("2023-05-02");
        updatedDiet.setMeal("Dinner");

        webTestClient.put()
                .uri("/diet/" + testDiet.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updatedDiet)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("Pizza")
                .jsonPath("$.calories").isEqualTo("700")
                .jsonPath("$.date").isEqualTo("2023-05-02")
                .jsonPath("$.meal").isEqualTo("Dinner");
    }

    @Test
    public void testDeleteDiet() {
        webTestClient.delete()
                .uri("/diet/" + testDiet.getId())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testGetDietsByDate() {
        webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/diet")
                        .queryParam("date", "2023-05-02")
                        .queryParam("userId", testUser.getId())
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.targetDiet").isEqualTo(2000)
                .jsonPath("$.totalCalories").isEqualTo(500)
                .jsonPath("$.achievedPercentage").isEqualTo(25.0)
                .jsonPath("$.diets[0].name").isEqualTo("Pasta")
                .jsonPath("$.diets[0].calories").isEqualTo("500")
                .jsonPath("$.diets[0].meal").isEqualTo("Lunch");
    }
}