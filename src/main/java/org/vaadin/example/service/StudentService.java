package org.vaadin.example.service;

import java.util.List;

import org.vaadin.example.model.dto.StudentDto;


public interface StudentService {
	
	void save(StudentDto studentDto);
	
	List<StudentDto> findAll();
	
	void deleteById(StudentDto studentDto);
}
