package com.cmpt276.studentdatabase.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cmpt276.studentdatabase.models.Student;
import com.cmpt276.studentdatabase.models.StudentRepository;

@Controller
public class StudentController {
    @Autowired
    private StudentRepository studentRepo;
    @GetMapping("/students/view")
    public String getAllStudents(Model model){
        System.out.println("Getting all students");
        // Get all users from database
        List<Student> students = studentRepo.findAll();
        // End of database call
        model.addAttribute("students", students);
        return "students/showAll";
    }
}
