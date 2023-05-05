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
public class GymController {



    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";

        RestAssured.basePath = "/gyms";
    }

    @Test
    public void addGymTest() {
        JSONObject gymObject = new JSONObject();
        try {
            gymObject.put("name", "Test Gym");
            gymObject.put("description", "Test Gym Description");
            gymObject.put("locationUrl", "http://example.com");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(gymObject.toString())
                .when()
                .post("/addGym");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("{\"status\":\"success\"}", responseBody);
    }

    @Test
    public void getAllGymsTest() {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONArray gymList = new JSONArray(responseBody);
        // Perform any assertions on the gymList if necessary
    }
    @Test
    public void postLikeTest() {
        long gymId = 1L;
        long userId = 1L;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .post("/{id}/like/{userId}", gymId, userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("{\"message\":\"Like added successfully!\"}", responseBody);
    }

    @Test
    public void postDislikeTest() {
        long gymId = 1L;
        long userId = 1L;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .post("/{id}/dislike/{userId}", gymId, userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("{\"message\":\"Dislike added successfully!\"}", responseBody);
    }

    @Test
    public void getUsersForGymTest() {
        long gymId = 1L;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("type", "like")
                .when()
                .get("/{id}/users", gymId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONArray userEmailList = new JSONArray(responseBody);
        // Perform any assertions on the userEmailList if necessary
    }
}