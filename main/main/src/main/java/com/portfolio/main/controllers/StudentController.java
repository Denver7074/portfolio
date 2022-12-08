package com.portfolio.main.controllers;

import com.portfolio.main.model.Image;
import com.portfolio.main.model.Student;
import com.portfolio.main.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public String students(@RequestParam(name = "FIO", required = false) String FIO,Principal principal, Model model) {
        model.addAttribute("students", studentService.listStudents(FIO));
        model.addAttribute("user", studentService.getUserByPrincipal(principal));
        return "students";
    }

    @GetMapping("/student/{id}")
    public String studentInfo(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("images", student.getImages());
        return "student-info";
    }

    @PostMapping("/student/create")
    public String createStudent(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2,@RequestParam("file3") MultipartFile file3, Student student, Principal principal) throws IOException {
        studentService.saveStudent(principal,student,file1,file2,file3);
        return "redirect:/";
    }

    @PostMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
       studentService.deleteStudent(id);
        return "redirect:/";
    }


}
