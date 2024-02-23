package com.cmpt276.studentdatabase.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpt276.studentdatabase.models.Student;
import com.cmpt276.studentdatabase.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;

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

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) {
        System.out.println("Add user");
        String newName = newStudent.get("name");
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight = Integer.parseInt(newStudent.get("height"));
        String newHairColor = newStudent.get("hair-color");
        float newGPA = Float.parseFloat(newStudent.get("gpa"));
        studentRepo.save(new Student(newName, newWeight, newHeight, newHairColor, newGPA));
        response.setStatus(201);
        return "students/addedStudent";
    }
}
