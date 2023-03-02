package com.example.demo.login;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path = "/login")
    public boolean login(@RequestBody LoginRequest loginRequest){



        return loginService.validateUser(loginRequest);

    }
}
