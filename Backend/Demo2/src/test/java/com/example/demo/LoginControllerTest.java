package com.example.demo;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.login.LoginRequest;
import com.example.demo.login.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.example.demo.appuser.AppUserRepository;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Api(tags = "Login")
@RestController
@AllArgsConstructor
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LoginControllerTest {

    @Autowired
    LoginService loginService;

    @Autowired
    AppUserRepository appUserRepository;


    @Before
    public void setUp(){
        RestAssured.baseURI = "http://coms-309-004.class.las.iastate.edu:8080";
    }

//    @ApiOperation(value = "Authenticate user")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully authenticated user"),
//            @ApiResponse(code = 400, message = "Bad request, invalid login information"),
//            @ApiResponse(code = 500, message = "Internal server error")
//    })
//    @PostMapping(path = "/login")
//    public ResponseEntity<String> login(
//            @ApiParam(value = "Login request object containing username and password", required = true)
//            @RequestBody LoginRequest loginRequest
//    ) {
//        boolean isValidUser = loginService.validateUser(loginRequest);
//
//        // create a Map object with a key-value pair representing the boolean result
//        Map<String, Boolean> resultMap = new HashMap<>();
//        resultMap.put("result", isValidUser);
//
//        // create JSON response
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonPayload;
//        try {
//            jsonPayload = mapper.writeValueAsString(resultMap);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//        // return response with 200 status code
//        return ResponseEntity.ok(jsonPayload);
//    }

    @Test
    public void testLogin(){
        AppUser testUser = new AppUser("testFirstName", "testLastName", "testemail@mail.com", "testpass", AppUserRole.USER);

        appUserRepository.save(testUser);

        LoginRequest loginRequest = new LoginRequest(testUser.getEmail(), testUser.getPassword());

        Response response = RestAssured.given()
                .contentType("application/json")
                .with().body(loginRequest)
                .when()
                .post("/login");

        int statusCode = response.getStatusCode();;
        assertEquals(200, statusCode);

        appUserRepository.delete(testUser);
    }
}