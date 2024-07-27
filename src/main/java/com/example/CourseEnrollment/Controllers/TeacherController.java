package com.example.CourseEnrollment.Controllers;

import com.example.CourseEnrollment.Models.Students;
import com.example.CourseEnrollment.Models.Teacher;
import com.example.CourseEnrollment.Services.StudentService;
import com.example.CourseEnrollment.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }



    @GetMapping
    public List<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }

    @GetMapping(path = "{teacherId}")
    public Teacher getTeacher(@PathVariable("teacherId") Long  teacherId){
        return teacherService.getTeacher(teacherId);
    }


    @PostMapping
    public void registerNewTeacher(@RequestBody Teacher teacher){
        teacherService.addNewTeacher(teacher);

    }

    @DeleteMapping(path = "{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Long teacherId){
        teacherService.deleteTeacher(teacherId);

    }

    @PutMapping(path = "{teacherId}")
    public void updateTeacher(
            @PathVariable("teacherId") Long studentId,
            @RequestParam( required = false) String firstName,
            @RequestParam( required = false) String lastName){
        teacherService.updateTeacher(studentId, firstName, lastName);
    }
}
