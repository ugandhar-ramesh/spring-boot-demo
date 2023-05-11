package com.example.demo.controller;


import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathVariable("studentId") int id){
        Student student = studentService.getStudentById(id);
        if (student == null) throw new StudentNotFoundException("Student id not found - " + id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable("studentId") Integer id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedStudent);

    }

    @PatchMapping("/students")
    public ResponseEntity<Student> patchStudent(@RequestParam("studentId") Integer id, @RequestBody Student student) {
        Student updatedStudent = studentService.patchStudent(id, student);
        if (updatedStudent != null) return ResponseEntity.ok(updatedStudent);
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/students/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") int id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
