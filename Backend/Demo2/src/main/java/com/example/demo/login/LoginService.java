package com.example.demo.login;


import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {



    @Autowired
    private  AppUserRepository appUserRepository;

    public boolean validateUser(LoginRequest loginRequest){

        System.out.println(loginRequest.toString());



        boolean result = appUserRepository.findAppUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword()).isPresent();

        System.out.println(result);


        return result;
    }
}
