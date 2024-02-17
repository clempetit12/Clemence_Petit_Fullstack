package com.example.exercice_student.controller;

import com.example.exercice_student.entity.Student;
import com.example.exercice_student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/base")
public class StudentRestController {

    private final StudentService studentService;


    @GetMapping(value = "student/{id}")
    public Student getStudent(@PathVariable("id") UUID id) {
       Student student = studentService.getById(id);
        return student;
    }
    @GetMapping(value = "students")
    public List<Student> getStudents() {
        return studentService.getAll();
    }



}
