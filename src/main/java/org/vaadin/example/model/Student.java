package org.vaadin.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity(name = "student")
public class Student implements Serializable{

	private static final long serialVersionUID = 2942711878087590380L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "first_name")
	@NotBlank(message = "First Name can not be empty")
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank(message = "Last Name can not be empty")
	private String lastName;
	
	@Column(name = "age")
	@Min(value = 0, message = "Age can not be less than 0")
	@Max(value = 99, message = "Age can not be greater than 99")
	private Integer age;
	
	@Column(name = "gender")
	@NotBlank(message = "Gender can not be empty")
	private String gender;

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
