package com.example.demo.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        boolean isValidUser = loginService.validateUser(loginRequest);

        // create a Map object with a key-value pair representing the boolean result
        Map<String, Boolean> resultMap = new HashMap<>();
        resultMap.put("result", isValidUser);

        // create JSON response
        ObjectMapper mapper = new ObjectMapper();
        String jsonPayload;
        try {
            jsonPayload = mapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // return response with 200 status code
        return ResponseEntity.ok(jsonPayload);
    }
}