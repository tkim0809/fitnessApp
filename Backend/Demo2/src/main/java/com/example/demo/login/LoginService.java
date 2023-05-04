package com.example.demo.login;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private AppUserRepository appUserRepository;

    public Long validateUser(LoginRequest loginRequest) {
        System.out.println(loginRequest.toString());

        Optional<AppUser> appUserOptional = appUserRepository.findAppUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        Long userId = appUserOptional.map(AppUser::getUserId).orElse(-1L);

        System.out.println(userId != -1L);
        return userId;
    }
}
