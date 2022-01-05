package org.vaadin.example.Enums;

public enum Genders {

	MALE("male"),
	FEMALE("female");
	
	private String value;
	
	private Genders(String value) {
		this.setString(value);
	}

	public String getString() {
		return value;
	}

	public void setString(String value) {
		this.value = value;
	}
	
	
}
