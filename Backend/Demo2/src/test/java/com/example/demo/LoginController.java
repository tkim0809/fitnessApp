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
public class LoginController {



    @Before
    public void setUp() {
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";
        RestAssured.basePath = "/login";
    }

    @Test
    public void loginTest() {
        String testUsername = "alex";
        String testPassword = "alexpassword";

        JSONObject loginRequest = new JSONObject();
        try {
            loginRequest.put("email", testUsername);
            loginRequest.put("password", testPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequest.toString())
                .when()
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        JSONObject responseBody = new JSONObject(response.getBody().asString());
        boolean result = responseBody.getBoolean("result");
        Long userId = responseBody.getLong("user_id");

        // Replace these assertions with the expected values for your test user
        assertEquals(true, result);
        assertEquals(39L, userId);
    }
    @Test
    public void loginFailTest() {
        String invalidUsername = "invaliduser@example.com";
        String invalidPassword = "wrongpassword";

        JSONObject loginRequest = new JSONObject();
        try {
            loginRequest.put("username", invalidUsername);
            loginRequest.put("password", invalidPassword);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginRequest.toString())
                .when()
                .post();

        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        JSONObject responseBody = new JSONObject(response.getBody().asString());
        boolean result = responseBody.getBoolean("result");
        Long userId = responseBody.getLong("user_id");

        assertEquals(false, result);
        assertEquals(-1L, userId);
    }
}
//
//    @Test
//    public void loginFailTest() {
//        JSONObject loginRequest = new JSONObject();
//        try {
//            loginRequest.put("username", "test65ss5579@example.com");
//            loginRequest.put("password", "testyokpassword");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Response response = RestAssured.given()
//                .contentType(ContentType.JSON)
//                .body(loginRequest.toString())
//                .when()
//                .post("/login");
//
//        int statusCode = response.getStatusCode();
//        assertEquals(200, statusCode);
//
//        String responseBody = response.getBody().asString();
//        try {
//            JSONObject responseObject = new JSONObject(responseBody);
//            assertEquals(false, responseObject.getBoolean("result"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
//}
