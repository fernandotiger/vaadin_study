package org.vaadin.example.views.course.factory;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.Enums.CourseType;
import org.vaadin.example.model.dto.ActionPage;
import org.vaadin.example.model.dto.ModelDto;
import org.vaadin.example.model.dto.StudentDto;
import org.vaadin.example.service.StudentService;
import org.vaadin.example.views.NavigationCrudListener;
import org.vaadin.example.views.ReloadFormAction;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;


@org.springframework.stereotype.Component
public class CourseMainLayoutFactory implements ReloadFormAction{

	@Autowired
	private StudentService studentService;
	
	private CourseMainLayout courseMainLayout;
	
	private class CourseMainLayout extends VerticalLayout {
		
		private TextField firstName;		
		private TextField lastName;		
		private ComboBox<String> courseType;		
		private Button buttonSave;		
		private Button buttonClear;		
		private Binder<StudentDto> binder;		
		private StudentDto studentDto;
		
		private NavigationCrudListener viewListener;
		
		public CourseMainLayout(NavigationCrudListener viewListener) {
			this.viewListener = viewListener;
		}
		
		public CourseMainLayout init() {
			firstName = new TextField("Course Name");		
			lastName = new TextField("Description");					
			courseType = new ComboBox<>("Course Type");		
			courseType.setItems(CourseType.PROGRAMMING.getString(), CourseType.DEVOPS.getString(), CourseType.DESIGN.getString());
			buttonSave = new Button("Save");
			buttonSave.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			buttonClear = new Button("Clear");
			buttonSave.addClickListener(event -> {
				
				try {
					binder.writeBean(studentDto);
				} catch (ValidationException e) {
					e.printStackTrace();
				}
				Notification notification = Notification.show("Data saved successfuly");
				notification.setPosition(Notification.Position.MIDDLE);
				studentService.save(studentDto);
				clearFields();
				this.viewListener.navigateNextPage(new ActionPage(1));
			});
			buttonClear.addClickListener(event ->{
				clearFields();
			});
			return this;
		}
		
		private void clearFields() {
			firstName.setValue("");
			lastName.setValue("");
			courseType.clear();
			studentDto = new StudentDto();
		}
		
		private void refreshFields(StudentDto studentDto) {
			this.studentDto.setId(studentDto.getId());
			firstName.setValue(studentDto.getFirstName());
			lastName.setValue(studentDto.getLastName());
			courseType.setValue(studentDto.getGender());
		}

		public CourseMainLayout bind() {
			binder = new Binder<StudentDto>(StudentDto.class);
			binder.forField(firstName).withValidator(name -> name != null && !name.isEmpty(), "First Name can not be empty").bind(StudentDto::getFirstName, StudentDto::setFirstName);
			binder.forField(lastName).withValidator(name ->  name != null && !name.isEmpty(), "Last Name can not be empty"). bind(StudentDto::getLastName, StudentDto::setLastName);
			binder.forField(courseType).withValidator(name ->  name != null && !name.isEmpty(), "Course Type can not be empty").bind(StudentDto::getGender, StudentDto::setGender);
			
			studentDto = new StudentDto();
			//binder.setBean(student);
			return this;
		}
		
		public Component layout() {
			FormLayout form = new FormLayout();
			courseType.setWidth("150px");
			form.add(firstName, lastName, courseType, buttonClear, buttonSave, buttonClear);
			form.setColspan(courseType, 2);
			
			return form;
		}
	}
	
	public Component createComponent(NavigationCrudListener viewListener) {
		courseMainLayout = new CourseMainLayout(viewListener).init().bind();
		return courseMainLayout.layout();
	}
	
	public void refresh(Optional<ModelDto> optionalModelDto) {
		if(optionalModelDto.isPresent()) {
			courseMainLayout.refreshFields((StudentDto) optionalModelDto.get());
		}
		
	}
}
