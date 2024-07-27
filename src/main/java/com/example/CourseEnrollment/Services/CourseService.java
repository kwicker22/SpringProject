package com.example.CourseEnrollment.Services;

import com.example.CourseEnrollment.Models.Courses;
import com.example.CourseEnrollment.Models.Teacher;
import com.example.CourseEnrollment.Repo.CourseRepo;
import com.example.CourseEnrollment.Repo.TeacherRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseService {
    private final CourseRepo courseRepo;

    @Autowired
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    @Autowired
    TeacherRepo teacherRepo;

    public List<Courses> getAllCourses() {
        return courseRepo.findAll();
    }

    @Transactional
    public void addCourse(Courses course) {
        if (course.getteacher() == null || course.getteacher().isEmpty()) {
            throw new RuntimeException("No teacher provided");
        }

        Set<Teacher> teacher = new HashSet<>();
        List<Long> missingTeacherIds = new ArrayList<>();

        for (Teacher teachers : course.getteacher()) {
            Long teacherId = teachers.getId();

            if (teacherId == null) {
                System.out.println("Teacher ID must not be null");
                continue;
            }

            Teacher existingteacher = teacherRepo.findById(teacherId)
                    .orElse(null);

            if (existingteacher != null) {
                teacher.add(existingteacher);
            } else {
                missingTeacherIds.add(teacherId);
            }
        }

        if (!missingTeacherIds.isEmpty()) {
            String message = "The following teachers were not found and not added: " + missingTeacherIds;
            System.out.println(message);
        }

        course.setTeacher(teacher);
        courseRepo.save(course);
    }

    public void deleteCourse(Long courseId){
        boolean courseExists = courseRepo.existsById(courseId);
        if (!courseExists) {
            System.out.println("Course not found");
        }
        courseRepo.deleteById(courseId);
    }
    @Transactional
    public void updateCourseName(Long courseId, String name){
        Courses course = courseRepo.findById(courseId).orElseThrow(() -> new IllegalStateException("Book not found"));
        if (name != null && !name.isEmpty() && !Objects.equals(course.getName(), name)){
            course.setName(name);
        }
    }


}

