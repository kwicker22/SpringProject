package com.example.CourseEnrollment.Controllers;

import com.example.CourseEnrollment.Models.Courses;
import com.example.CourseEnrollment.Models.Enrollments;
import com.example.CourseEnrollment.Services.CourseService;
import com.example.CourseEnrollment.Services.EnrollmentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/enrollment")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping(path = "SeeAllEnrollments")
    public List<Enrollments> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    @PostMapping(path = "AddEnrollment")
    public void addEnrollment(@RequestBody Enrollments enrollment) {
        enrollmentService.addEnrollment(enrollment);
    }
    @DeleteMapping(path = "/Enrollment{enrollmentId}")
    public void deleteEnrollment(@PathVariable("enrollmentId") Long enrollmentId) {
        enrollmentService.deleteEnrollment(enrollmentId);
    }

    @PutMapping("/{enrollmentId}/status")
    public ResponseEntity<?> updateEnrollmentStatus(
            @PathVariable Long enrollmentId,
            @RequestParam Enrollments.EnrollmentStatus status) {

        try {
            Enrollments updatedEnrollment = enrollmentService.updateEnrollmentStatus(enrollmentId, status);
            return ResponseEntity.ok(updatedEnrollment);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
