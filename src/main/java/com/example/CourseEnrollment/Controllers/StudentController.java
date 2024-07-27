package com.example.CourseEnrollment.Controllers;

import com.example.CourseEnrollment.Models.Students;
import com.example.CourseEnrollment.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }



    @GetMapping
    public List<Students> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping(path = "{studentId}")
    public Students getStudent(@PathVariable("studentId") Long  studentId){
        return studentService.getStudent(studentId);
    }


    @PostMapping
    public void registerNewStudent(@RequestBody Students student){
        studentService.addNewStudent(student);

    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);

    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam( required = false) String firstName,
            @RequestParam( required = false) String lastName,
            @RequestParam( required = false) String email) {
        studentService.updateStudent(studentId, firstName, lastName, email);
    }

}
