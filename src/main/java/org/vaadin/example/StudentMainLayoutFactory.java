package org.vaadin.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.vaadin.example.Enums.Genders;
import org.vaadin.example.model.Student;
import org.vaadin.example.model.dto.StudentDto;
import org.vaadin.example.service.StudentService;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.converter.StringToIntegerConverter;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;


@org.springframework.stereotype.Component
public class StudentMainLayoutFactory {

	@Autowired
	private StudentService studentService;
	@Configurable
	private class StudentMainLayout extends VerticalLayout {
		
		private TextField firstName;		
		private TextField lastName;		
		private TextField age;		
		private ComboBox<String> gender;		
		private Button buttonSave;		
		private Button buttonClear;		
		private Binder<StudentDto> binder;		
		private StudentDto studentDto;
		
		public StudentMainLayout init() {
			firstName = new TextField("First Name");		
			lastName = new TextField("Last Name");				
			age = new TextField("Age");				
			gender = new ComboBox<>("Gender");		
			gender.setItems(Genders.MALE.getString(), Genders.FEMALE.getString());
			buttonSave = new Button("Save");
			buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			buttonClear = new Button("Clear");
			buttonSave.addClickListener(event -> {
				System.out.println(binder.getBean());
				try {
					binder.writeBean(studentDto);
				} catch (ValidationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(studentDto.getFirstName());
				Notification notification = Notification.show("Data saved successfuly");
				notification.setPosition(Notification.Position.MIDDLE);
				studentService.save(studentDto);
			});
			buttonClear.addClickListener(event ->{
				clearFields();
			});
			return this;
		}
		
		private void clearFields() {
			firstName.setValue("");
			lastName.setValue("");
			age.setValue("");
			gender.clear();
		}

		public StudentMainLayout bind() {
			binder = new Binder<StudentDto>(StudentDto.class);
			binder.forField(firstName).withValidator(name -> name != null && !name.isEmpty(), "First Name can not be empty").bind(StudentDto::getFirstName, StudentDto::setFirstName);
			binder.forField(lastName).withValidator(name ->  name != null && !name.isEmpty(), "Last Name can not be empty"). bind(StudentDto::getLastName, StudentDto::setLastName);
			binder.forField(age).withConverter(new StringToIntegerConverter("Please enter an number")).withValidator(ag ->  ag != null && (ag > 0 && ag < 100), "Age should be 1 - 99").bind(StudentDto::getAge, StudentDto::setAge);
			binder.forField(gender).withValidator(name ->  name != null && !name.isEmpty(), "First Name can not be empty").bind(StudentDto::getGender, StudentDto::setGender);
			
			studentDto = new StudentDto();
			//binder.setBean(student);
			return this;
		}
		
		public Component layout() {
			FormLayout form = new FormLayout();
			form.add(firstName, lastName, age, gender, buttonSave, buttonClear);
			return form;
		}
	}
	
	public Component createComponent() {
		return new StudentMainLayout().init().bind().layout();
	}
}
