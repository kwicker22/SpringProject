package com.example.CourseEnrollment.Services;


import com.example.CourseEnrollment.Models.Students;
import com.example.CourseEnrollment.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }




    public List<Students> getStudents(){
        return studentRepo.findAll();
    }

    public Students getStudent(Long studentId) {
        boolean exists = studentRepo.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        return studentRepo.findById(studentId).get();
    }

    public void addNewStudent(Students student) {
        Optional<Students> studentOptional = studentRepo.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        studentRepo.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepo.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepo.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String firstName, String lastName, String email) {
        Students student = studentRepo.findById(studentId).orElseThrow(() -> new IllegalStateException(
                "student with id" + studentId + "does not exist" ));

        if (firstName != null && !firstName.isEmpty() && !Objects.equals(student.getFirstName(), firstName)){
            student.setFirstName(firstName);
        }
        if (lastName!= null && !lastName.isEmpty() && !Objects.equals(student.getLastName(), lastName)){
            student.setLastName(lastName);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)) {
            Optional<Students> studentOptional = studentRepo.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}