package org.vaadin.example.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.example.model.Student;
import org.vaadin.example.model.dto.StudentDto;
import org.vaadin.example.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService, Serializable{

	@Autowired
	private StudentRepository repository;
	
	@Override
	public void save(StudentDto studentDto) {
		Student student = studentDto.getEntity();
		
		repository.save(student);
	}

}
