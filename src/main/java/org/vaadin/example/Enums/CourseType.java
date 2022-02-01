package org.vaadin.example.Enums;

public enum CourseType {

	PROGRAMMING("programming"),
	DESIGN("design"),
	DEVOPS("devops"),
	DATABASE("database");
	
	private String value;
	
	private CourseType(String value) {
		this.setString(value);
	}

	public String getString() {
		return value;
	}

	public void setString(String value) {
		this.value = value;
	}
	
	
}
