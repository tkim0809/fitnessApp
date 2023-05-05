package com.example.demo;

import com.example.demo.appuser.AppUserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class FriendsController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";
        RestAssured.basePath = "/api/friends";
    }

    @Test
    public void testAddFriend() {
        Long userId = 37L; // Replace with an actual user ID in your database
        String friendEmail = "taewank@iastate.edu"; // Replace with an actual friend's email

        String jsonBody = String.format("{\"email\": \"%s\"}", friendEmail);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/{userId}/add", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("{\"message\" : \"success\"}", responseBody);
    }

    @Test
    public void testGetFriends() {
        Long userId = 1L; // Replace with an actual user ID in your database

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{userId}/friends", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONArray friendList = new JSONArray(responseBody);
        // Perform any assertions on the friendList if necessary
    }
}