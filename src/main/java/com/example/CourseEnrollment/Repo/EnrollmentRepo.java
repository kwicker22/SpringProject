package com.example.CourseEnrollment.Repo;

import com.example.CourseEnrollment.Models.Enrollments;
import com.example.CourseEnrollment.Models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollments, Long> {
}
