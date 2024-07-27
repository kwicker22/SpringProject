package com.example.CourseEnrollment.Controllers;

import com.example.CourseEnrollment.Models.Courses;
import com.example.CourseEnrollment.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(path = "SeeAllCourses")
    public List<Courses> getAllCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping(path = "AddCourses")
    public void addBook(@RequestBody Courses course) {
        courseService.addCourse(course);
    }
    @DeleteMapping(path = "/Course{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
    }
    @PutMapping(path = "Update{courseId}name")
    public void updateCourse(
            @PathVariable("courseId") Long courseId
            , @RequestParam(required = false) String name) {
        courseService.updateCourseName(courseId, name);
    }
}


