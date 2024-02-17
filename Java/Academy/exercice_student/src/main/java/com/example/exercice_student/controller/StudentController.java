package com.example.exercice_student.controller;

import com.example.exercice_student.entity.Student;
import com.example.exercice_student.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class StudentController {


    private final StudentService studentService;


    @GetMapping
    public String showHome() {
        return "home";
    }

    @GetMapping(value = "/list")
    public String showAllStudents(Model model) {
        List<Student> studentList = studentService.getAll();
        model.addAttribute("students", studentList);
        return "studentList";

    }

    @GetMapping(value = "/detail/{studentId}")
    public String showStudent(@PathVariable("studentId") UUID id, Model model) {
        Student student = studentService.getById(id);
        model.addAttribute("student", student);
        return "student/student";

    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/addForm";
    }

    @PostMapping(value = "/add")
    public String submitStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "student/addForm";
        } else{
            if(student.getId() != null) {
                studentService.update(student.getId(),student);
            } else {
                studentService.add(student);
            }
            return "redirect:/list";
        }


    }

    @GetMapping(value = "/search")
    public String searchStudentByName(@RequestParam(name = "name", required = false) String name,
                                      Model model) {
        List<Student> studentList = studentService.getByLastNameOrFirstName(name);
        for (Student s : studentList
        ) {
            System.out.println(s);
        }
        if (!studentList.isEmpty()) {
            model.addAttribute("students", studentList);
            return "studentList";
        } else {
            return "error";
        }

    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam("studentId") UUID id) {
        System.out.println("delete");
        Student student = studentService.getById(id);
        System.out.println(student);
        if (student != null) {
            studentService.deleteStudent(student);
            return "redirect:/list";
        }
        return "error";
    }

    @GetMapping("/update/{studentId}")
    public String formUpdateStudent(@PathVariable("studentId") UUID id,Model model){
        Student student = studentService.getById(id);
        model.addAttribute("student",student);
        return "student/addForm";
    }



}


