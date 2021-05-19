package com.example.springboot.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.example.springboot.exception.ApiRequestException;
import com.example.springboot.models.Student;
import com.example.springboot.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

	@Autowired
    private StudentRepository studentRepository;
    
	// private final StudentRepository studentRepository;

	// @Autowired
	// private StudentService(StudentRepository studentRepository) {
	// 	this.studentRepository = studentRepository;
	// }

    public List<Student> getStudents() {
		return studentRepository.findAll();
	}

    public void addStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			// throw new IllegalStateException("This email is already taken!");
			throw new ApiRequestException("This email is already taken!");
		} 
		studentRepository.save(student);
	}

	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if (!exists) {
			throw new ApiRequestException("Student with id: " + studentId + " does not exists.");
		}
		studentRepository.deleteById(studentId);
	}

	// The @Transactional annotation must be for the Service Layer
	// So for use this spring annotation the @Autowired annotations must be for the DAO layer
	@Transactional
    public void updateStudent(Long studentId, String name, String email) {
		// boolean exists = studentRepository.existsById(studentId);
		// if (!exists) {
		// 	throw new IllegalStateException("Student with id: " + studentId + " does not exists.");
		// }
		// Optional<Student> studentOptional = studentRepository.findById(studentId);
		// Student student = studentOptional.get();
		// studentRepository.save(student);
		
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new ApiRequestException("Student with id: " + studentId + " does not exists."));

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new ApiRequestException("This email is already taken!");
			}
			student.setEmail(email);
		}
	}

    public Student findStudent(Long studentId) {
		// Student student = studentRepository.findById(studentId)
		// 	.orElseThrow(() -> new IllegalStateException("Student with id: " + studentId + " does not exists."));
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new ApiRequestException("Student with id: " + studentId + " does not exists."));
		return student;
    }
}
