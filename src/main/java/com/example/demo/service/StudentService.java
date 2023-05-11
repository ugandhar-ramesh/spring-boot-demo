package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    public Student getStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }


    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Integer id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) return null;
        Student existingStudent = optionalStudent.get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setEmail(student.getEmail());
            return studentRepository.save(existingStudent);
    }

    public Student patchStudent(Integer id, Student student) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()) return null;
        Student existingStudent = optionalStudent.get();
        if (student.getFirstName() != null) existingStudent.setFirstName(student.getFirstName());
        if (student.getLastName() != null)  existingStudent.setLastName(student.getLastName());
        if (student.getEmail() != null)     existingStudent.setEmail(student.getEmail());
        return studentRepository.save(existingStudent);

    }

    public void deleteStudent(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) throw new StudentNotFoundException("Student id not found - " + id);
        studentRepository.deleteById(id);

    }
}
