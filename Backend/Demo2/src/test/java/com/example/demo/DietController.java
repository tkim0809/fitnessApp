package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DietController {



    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";
        RestAssured.basePath = "";
    }

    @Test
    public void addDietGoalTest() {
        long userId = 1L;
        JSONObject dietGoalObject = new JSONObject();
        try {
            dietGoalObject.put("dietGoalValue", 2000);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dietGoalObject.toString())
                .when()
                .post("/dietgoal/{userId}", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void getDietGoalTest() {
        long userId = 1L;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/dietgoal/{userId}", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void getDietByUserAndDateTest() {
        long userId = 1L;
        String date = "2023-05-04";

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/diet/{userId}/{date}", userId, date);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void addDietTest() {
        long userId = 1L;
        JSONObject dietObject = new JSONObject();
        try {
            dietObject.put("name", "Test Food");
            dietObject.put("calories", 100);
            dietObject.put("date", "2023-05-04");
            dietObject.put("meal", "Breakfast");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(dietObject.toString())
                .when()
                .post("/diet/{userId}", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }

    @Test
    public void deleteDietTest() {
        long dietId = 58L;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/diet/{id}", dietId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }
}
