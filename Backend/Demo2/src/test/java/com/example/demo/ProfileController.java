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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class ProfileController {



    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";

        RestAssured.basePath = "/profile";
    }

    @Test
    public void testGetProfile() {
        Long userId = 35L; // Replace with an actual user ID in your database

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{userId}", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONObject profileObject = new JSONObject(responseBody);
        // Perform any assertions on the profileObject if necessary
    }

    @Test
    public void testCreateProfile() {
        Long userId = 39L; // Replace with an actual user ID in your database

        JSONObject profileObject = new JSONObject();
        // Add the required fields in the profileObject
        profileObject.put("field1", "value1");
        profileObject.put("field2", "value2");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(profileObject.toString())
                .when()
                .post("/{userId}", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONObject createdProfileObject = new JSONObject(responseBody);
        // Perform any assertions on the createdProfileObject if necessary
    }

    @Test
    public void testUpdateProfile() {
        Long userId = 35L; // Replace with an actual user ID in your database

        JSONObject updatedProfileObject = new JSONObject();
        // Add the required fields in the updatedProfileObject
        updatedProfileObject.put("field1", "newValue1");
        updatedProfileObject.put("field2", "newValue2");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(updatedProfileObject.toString())
                .when()
                .put("/{userId}", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONObject updatedProfile = new JSONObject(responseBody);
        // Perform any assertions on the updatedProfile if necessary
    }
}
