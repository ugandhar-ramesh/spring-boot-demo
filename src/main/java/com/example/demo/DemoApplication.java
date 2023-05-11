package com.example.demo;

import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
//			createStudent(studentDAO);
//			createMultipleStudents(studentDAO);
//			readStudent(studentDAO);
//			queryStudents(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
			System.out.println("Hello World!");
		};
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int id = 4;
		studentDAO.delete(id);
		System.out.println("Deleted the student with Id: " + id);
	}

	private void updateStudent(StudentDAO studentDAO) {

		//retrieve student based on id
		Student myStudent = studentDAO.findById(3);

		//update the student
		myStudent.setLastName("Smith");
		studentDAO.update(myStudent);

		//display the updated student
		System.out.println(myStudent);
	}

	private void queryStudents(StudentDAO studentDAO) {
		List<Student> students = studentDAO.findAll();
		students.forEach(System.out::println);
	}

	private void readStudent(StudentDAO studentDAO) {
		Student tempStudent = studentDAO.findById(1);
		System.out.println(tempStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		//create multiple students
		Student tempStudent1 = new Student("Foo", "Bar", "me12b146@gmail.com");
		Student tempStudent2 = new Student("John", "Doe", "ugandhar_iitm@hotmail.com");
		Student tempStudent3 = new Student("Paul", "Smith", "me12b146@smail.iitm.ac.in");

		//save the student objects
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

		System.out.println("Students are saved...");
	}

	private void createStudent(StudentDAO studentDAO) {
		//create the student object
		Student tempStudent = new Student("Ugandhar", "Ramesh", "ugandhar.iitm@gmail.com");

		//save the student object
		studentDAO.save(tempStudent);

		System.out.println("ID of the saved student: " + tempStudent.getId());
	}

}
