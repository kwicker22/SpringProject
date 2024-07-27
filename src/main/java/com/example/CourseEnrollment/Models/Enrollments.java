package com.example.CourseEnrollment.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "enrollments")
public class Enrollments {

    @Id
    @SequenceGenerator(
            name = "enrollment_sequence",
            sequenceName = "enrollment_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "enrollment_sequence"
    )
    private Long id;

    public enum EnrollmentStatus {
        PENDING,
        APPROVED,
        REJECTED,
        COMPLETED
    }

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    @ManyToMany
    @JoinTable(
            name = "enrollment_courses",
            joinColumns = @JoinColumn(name = "enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Courses> courses;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Students student;

    // Default constructor
    public Enrollments() {
    }

    // Constructor
    public Enrollments(Long id, EnrollmentStatus status, Set<Courses> courses, Students student) {
        this.id = id;
        this.status = status;
        this.courses = courses;
        this.student = student;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }

    public Set<Courses> getCourses() {
        return courses;
    }

    public void setCourses(Set<Courses> courses) {
        this.courses = courses;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Enrollments{" +
                "id=" + id +
                ", status=" + status +
                ", courses=" + courses +
                ", student=" + student +
                '}';
    }
}
