package org.vaadin.example.model.dto;

import org.vaadin.example.model.Student;

public class StudentDto implements ModelDto{

	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private Integer age;
	
	private String gender;
	
	public Student getEntity() {
		Student student = new Student();
		student.setId(id);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setAge(age);
		student.setGender(gender);
		return student;
	}
	
	public static StudentDto getDto(Student entity) {
		StudentDto student = new StudentDto();
		student.setId(entity.getId());
		student.setFirstName(entity.getFirstName());
		student.setLastName(entity.getLastName());
		student.setAge(entity.getAge());
		student.setGender(entity.getGender());
		return student;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
