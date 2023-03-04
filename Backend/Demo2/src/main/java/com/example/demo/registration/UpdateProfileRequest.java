package com.example.demo.registration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateProfileRequest {
    private String email;
    private String firstName;
    private String lastName;




}