package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository){
        return args -> {

            Student mariam = new Student(
                    "Mariam",
                    "mariam.ja@gmail.com",
                    LocalDate.of(2000, JANUARY, 5)


            );

            Student chandra = new Student(
                    "Chandra",
                    "chandra3@iastate.edu",
                    LocalDate.of(2002, MARCH, 16)


            );

            repository.saveAll(
                    List.of(mariam, chandra)
            );

        };
    }


}
