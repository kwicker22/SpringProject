package com.example.CourseEnrollment.Config;

import com.example.CourseEnrollment.Models.Students;
import com.example.CourseEnrollment.Models.Teacher;
import com.example.CourseEnrollment.Repo.StudentRepo;
import com.example.CourseEnrollment.Repo.TeacherRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class TeacherConfig {
    @Bean
    CommandLineRunner teachercommandLineRunner(TeacherRepo repo){
    return args -> {

        Teacher trey= new Teacher(1L,
                "trey",
                "trey",
                null
        );
        repo.save(trey);



    };
}}
