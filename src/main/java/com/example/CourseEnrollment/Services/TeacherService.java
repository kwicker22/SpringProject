package com.example.CourseEnrollment.Services;


import com.example.CourseEnrollment.Models.Courses;
import com.example.CourseEnrollment.Models.Teacher;

import com.example.CourseEnrollment.Repo.CourseRepo;
import com.example.CourseEnrollment.Repo.TeacherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final CourseRepo courseRepo;

    @Autowired
    public TeacherService(TeacherRepo teacherRepo, CourseRepo courseRepo) {
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
    }




    public List<Teacher> getTeachers(){
        return teacherRepo.findAll();
    }

    public Teacher getTeacher(Long teacherId) {
        boolean exists = teacherRepo.existsById(teacherId);
        if (!exists) {
            throw new IllegalStateException("teacher with id " + teacherId + " does not exist");
        }
        return teacherRepo.findById(teacherId).get();
    }
    @Transactional
    public void addNewTeacher(Teacher teacher) {
//        if (teacher.getCourse() == null || teacher.getCourse().isEmpty()) {
//            throw new RuntimeException(teacher.toString());
//        }
//
//        Set<Courses> courses = new HashSet<>();
//        List<Long> missingCourseIds = new ArrayList<>();
//
//        for (Courses course : teacher.getCourse()) {
//            Long courseId = course.getId();
//
//            if (courseId == null) {
//                System.out.println("Course ID must not be null");
//                continue;
//            }
//
//             Courses existingcourse = courseRepo.findById(courseId)
//                    .orElse(null);
//
//            if (existingcourse != null) {
//                courses.add(existingcourse);
//            } else {
//                missingCourseIds.add(courseId);
//            }
//
//        if (!missingCourseIds.isEmpty()) {
//            String message = "The following courses were not found and not added: " + missingCourseIds;
//            System.out.println(message);
//        }
//
//        teacher.setCourse(courses);
        teacherRepo.save(teacher);
    }

    public void deleteTeacher(Long teacherId) {
        boolean exists = teacherRepo.existsById(teacherId);
        if (!exists) {
            throw new IllegalStateException("teacher with id " + teacherId + " does not exist");
        }
    }

    @Transactional
    public void updateTeacher(Long teacherId, String firstName, String lastName) {
        Teacher teacher = teacherRepo.findById(teacherId).orElseThrow(() -> new IllegalStateException(
                "teacher with id" + teacherId + "does not exist" ));

        if (firstName != null && !firstName.isEmpty() && !Objects.equals(teacher.getFirstName(), firstName)){
            teacher.setFirstName(firstName);
        }
        if (lastName!= null && !lastName.isEmpty() && !Objects.equals(teacher.getLastName(), lastName)){
            teacher.setLastName(lastName);
        }

    }
}

