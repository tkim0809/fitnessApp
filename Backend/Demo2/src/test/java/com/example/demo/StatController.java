package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class StatController {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";
    }

    @Test
    public void testSaveWorkout() {
        JSONObject workoutObject = new JSONObject();
        // Add the required fields in the workoutObject
        workoutObject.put("field1", "value1");
        workoutObject.put("field2", "value2");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(workoutObject.toString())
                .when()
                .post("/stats/new");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("{\"message\":\"success\"}", responseBody);
    }

    @Test
    public void testGetAllWorkouts() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/Workouts");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONArray workoutList = new JSONArray(responseBody);
        // Perform any assertions on the workoutList if necessary
    }

    @Test
    public void testFindWorkoutById() {
        int workoutId = 23; // Replace with an actual workout ID in your database

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/workouts/{workoutId}", workoutId);

        int statusCode = response.getStatusCode();
        assertTrue(statusCode == 200 || statusCode == 404);

        if (statusCode == 200) {
            String responseBody = response.getBody().asString();
            JSONObject workoutObject = new JSONObject(responseBody);
            // Perform any assertions on the workoutObject if necessary
        }
    }
}