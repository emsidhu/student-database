package com.cmpt276.studentdatabase.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        List<Student> students = studentRepo.findAllByOrderByIdAsc();
        // End of database call
        model.addAttribute("students", students);
        return "students/showAll";
    }

    @GetMapping("students/edit/{id}")
    public String getEditForm(@PathVariable("id") int id, Model model) {
        Student student = studentRepo.findById(id);
        model.addAttribute("student", student);
        return "students/editStudent";
    }
    

    @PostMapping("/students/add")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response) {
        System.out.println("Add student");

        String newName = newStudent.get("name");
        String newHairColor = newStudent.get("hairColor");
        String newEyeColor = newStudent.get("eyeColor");
        int newAge = Integer.parseInt(newStudent.get("age"));
        int newWeight = Integer.parseInt(newStudent.get("weight"));
        int newHeight = Integer.parseInt(newStudent.get("height"));
        float newGPA = Float.parseFloat(newStudent.get("gpa"));
        studentRepo.save(new Student(newName, newHairColor, newEyeColor, newAge, newWeight, newHeight, newGPA));

        response.setStatus(201);
        return "redirect:/students/view";
    }
    
    @PostMapping("/students/edit")
    public String updateProduct(@ModelAttribute("student") Student updatedStudent, HttpServletResponse response) {
        System.out.println("Edit student");
        studentRepo.save(updatedStudent);
        response.setStatus(200);
        return "redirect:/students/view";
    }



    @DeleteMapping("/students/delete/{id}")
    public void deleteStudent(@PathVariable int id, HttpServletResponse response) {
        System.out.println("Delete student");
        studentRepo.deleteById(id);
        response.setStatus(204);
    }

}
