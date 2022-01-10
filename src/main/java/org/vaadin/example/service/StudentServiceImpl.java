package org.vaadin.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.example.model.Student;
import org.vaadin.example.model.dto.StudentDto;
import org.vaadin.example.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository repository;
	
	//@Autowired
    //private ModelMapper modelMapper;
	
	@Override
	public void save(StudentDto studentDto) {
		Student student = studentDto.getEntity();
		
		repository.save(student);
	}

	@Override
	public List<StudentDto> findAll() {
		return repository.findAll().stream().map(StudentDto::getDto).collect(Collectors.toList());
	}

	@Override
	public void deleteById(StudentDto studentDto) {
		repository.deleteById(studentDto.getId());
		
	}

}
