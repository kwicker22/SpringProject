package com.example.CourseEnrollment.Config;

import com.example.CourseEnrollment.Models.Courses;

import com.example.CourseEnrollment.Repo.CourseRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.time.LocalDate;
import java.time.Month;

@Configuration
public class CourseConfig {
    @Bean
    CommandLineRunner commandLineRunner (CourseRepo repo) {
        return args -> {

            Courses math = new Courses(1L,
                    "math",
                    3,
                    null);
            repo.save(math);
        };
    }}

