package com.example.CourseEnrollment.Services;

import com.example.CourseEnrollment.Models.Courses;
import com.example.CourseEnrollment.Models.Enrollments;
import com.example.CourseEnrollment.Models.Enrollments.EnrollmentStatus;
import com.example.CourseEnrollment.Models.Students;
import com.example.CourseEnrollment.Repo.CourseRepo;
import com.example.CourseEnrollment.Repo.EnrollmentRepo;
import com.example.CourseEnrollment.Repo.StudentRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.CourseEnrollment.Models.Enrollments.EnrollmentStatus.APPROVED;

@Service
public class EnrollmentService {
    @Autowired
    private EnrollmentRepo enrollmentsRepository;

    @Autowired
    private CourseRepo courseRepository;

    @Autowired
    private StudentRepo studentRepository;

    public List<Enrollments> getAllEnrollments(){
        return enrollmentsRepository.findAll();
    }

    public Enrollments getEnrollment(Long enrollmentId) {
        boolean exists = studentRepository.existsById(enrollmentId);
        if (!exists) {
            throw new IllegalStateException("enrollment with id " + enrollmentId + " does not exist");
        }
        return enrollmentsRepository.findById(enrollmentId).get();
    }

    public void addEnrollment(Enrollments enrollment) {
        Set<Courses> validCourses = new HashSet<>();
        for (Courses course : enrollment.getCourses()) {
            Optional<Courses> existingCourse = courseRepository.findById(course.getId());
            if (existingCourse.isPresent()) {
                validCourses.add(existingCourse.get());
            } else {
                throw new EntityNotFoundException("Course with ID " + course.getId() + " does not exist.");
            }
        }
        enrollment.setCourses(validCourses);

        Students student = enrollment.getStudent();
        if (student != null) {
            Optional<Students> existingStudent = studentRepository.findById(student.getId());
            if (existingStudent.isPresent()) {
                enrollment.setStudent(existingStudent.get());
            } else {
                throw new EntityNotFoundException("Student with ID " + student.getId() + " does not exist.");
            }
        } else {
            throw new EntityNotFoundException("No student specified for enrollment.");
        }

        enrollmentsRepository.save(enrollment);
    }


    public void deleteEnrollment(Long enrollmentId) {
            boolean exists = enrollmentsRepository.existsById(enrollmentId);
            if (!exists) {
                throw new IllegalStateException("enrollment with id " + enrollmentId + " does not exist");
            }
            enrollmentsRepository.deleteById(enrollmentId);

    }

    public Enrollments updateEnrollmentStatus(Long enrollmentId, EnrollmentStatus newStatus) {
        Enrollments enrollment = enrollmentsRepository.findById(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment with ID " + enrollmentId + " does not exist."));

        if (!isTransitionAllowed(enrollment.getStatus(), newStatus)) {
            throw new IllegalArgumentException("Status transition from " + enrollment.getStatus() + " to " + newStatus + " is not allowed.");
        }

        enrollment.setStatus(newStatus);


        return enrollmentsRepository.save(enrollment);
    }
        private boolean isTransitionAllowed( EnrollmentStatus currentStatus, EnrollmentStatus newStatus) {
            // Define valid transitions
            switch (currentStatus) {
                case PENDING:
                    return newStatus == APPROVED || newStatus == EnrollmentStatus.REJECTED;
                case APPROVED:
                    return newStatus == EnrollmentStatus.COMPLETED || newStatus == EnrollmentStatus.REJECTED;
                case REJECTED:
                    return false;
                case COMPLETED:
                    return false;
                default:
                    throw new IllegalArgumentException("Unknown status: " + currentStatus);
            }
        }
    }



