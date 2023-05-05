package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LeaderboardsController {



    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";
        RestAssured.basePath = "/leaderboards";
    }

    @Test
    public void saveWorkoutTest() {
        JSONObject leaderboardObject = new JSONObject();
        leaderboardObject.put("name", "Test User");
        leaderboardObject.put("pushups", 50);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(leaderboardObject.toString())
                .when()
                .post("/new");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("{\"message\":\"success\"}", responseBody);
    }

    @Test
    public void getAllUsersTest() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Perform any assertions on the leaderboard entries if necessary
    }

    @Test
    public void findOwnerByIdTest() {
        int leaderboardId = 1;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{leaderboardsId}", leaderboardId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        // Perform any assertions on the returned leaderboard entry if necessary
    }
}