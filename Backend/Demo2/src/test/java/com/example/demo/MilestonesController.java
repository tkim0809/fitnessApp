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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MilestonesController {


    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";
        RestAssured.basePath = "/Milestones";
    }

    @Test
    public void saveMilestoneTest() {
        JSONObject milestoneObject = new JSONObject();
        milestoneObject.put("milestoneName", "Test Milestone");
        milestoneObject.put("milestoneReps", "Test Milestone Reps");
        milestoneObject.put("milestoneSets", "Test Milestone Sets");
        milestoneObject.put("milestoneWeight", "Test Milestone Weight");
        milestoneObject.put("userId", "37");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(milestoneObject.toString())
                .when()
                .post("/new");

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("{\"message\":\"success\"}", responseBody);
    }

    @Test
    public void updateMilestoneStatusTest() {
        int milestoneId = 45;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .put("/{milestoneId}/completed", milestoneId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        assertEquals("{\"message\":\"success\"}", responseBody);
    }

    @Test
    public void getAllMilestonesTest() {
        int userId = 37;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/AllMilestones/{userId}", userId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONArray milestoneList = new JSONArray(responseBody);
        // Perform any assertions on the milestoneList if necessary
    }

    @Test
    public void findMilestoneByIdTest() {
        int milestoneId = 45;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get("/{milestoneId}", milestoneId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        JSONObject milestoneObject = new JSONObject(responseBody);
        // Perform any assertions on the milestoneObject if necessary
    }

    @Test
    public void deleteMilestoneTest() {
        int milestoneId = 45;

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .delete("/delete/{id}", milestoneId);

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);
    }
}