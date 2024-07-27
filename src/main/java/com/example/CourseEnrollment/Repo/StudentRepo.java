package com.example.CourseEnrollment.Repo;


import com.example.CourseEnrollment.Models.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo
        extends JpaRepository<Students, Long> {

    @Query("SELECT s FROM Students s WHERE s.email = ?1")
    Optional<Students> findStudentByEmail(String email);


}
