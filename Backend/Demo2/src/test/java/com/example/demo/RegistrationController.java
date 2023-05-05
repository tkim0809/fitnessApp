package com.example.demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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
public class RegistrationController {



    @Before
    public void setUp() {

        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";
        RestAssured.basePath = "api/v1/registration";
    }

    @Test
    public void registerTest() {
        String testEmail = "tescdt45jcc6965ss5579@example.com";

        JSONObject registrationRequest = new JSONObject();
        try {
            registrationRequest.put("email", testEmail);
            registrationRequest.put("password", "testyjokpassword");
            registrationRequest.put("firstName", "Test");
            registrationRequest.put("lastName", "User");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(registrationRequest.toString())
                .when()
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        String expectedResponse = String.format("{'email':%s}", testEmail);
        assertEquals(expectedResponse, responseBody);
    }


    @Test
    public void updateProfileTest() {
        JSONObject updateProfileRequest = new JSONObject();
        try {
            updateProfileRequest.put("firstName", "Updated");
            updateProfileRequest.put("lastName", "User");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(updateProfileRequest.toString())
                .when()
                .put("/test6969@example.com");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("Profile updated successfully", responseBody);
    }
}
