package com.example.CourseEnrollment.Repo;

import com.example.CourseEnrollment.Models.Courses;
import com.example.CourseEnrollment.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepo extends JpaRepository<Courses, Long> {
}
