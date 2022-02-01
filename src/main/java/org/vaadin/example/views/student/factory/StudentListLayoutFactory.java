package org.vaadin.example.views.student.factory;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.model.dto.ActionPage;
import org.vaadin.example.model.dto.ModelDto;
import org.vaadin.example.model.dto.StudentDto;
import org.vaadin.example.service.StudentService;
import org.vaadin.example.views.NavigationCrudListener;
import org.vaadin.example.views.ReloadListViewAction;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;


@org.springframework.stereotype.Component
public class StudentListLayoutFactory implements ReloadListViewAction{

	@Autowired
	private StudentService studentService;
	
	private StudentMainLayout studentMainLayout;
	
	private class StudentMainLayout extends VerticalLayout {
		
		private Grid<StudentDto> grid;		
						
		private Button buttonEdit;
		private Button buttonRemove;	
		private StudentDto studentDto;
		
		private NavigationCrudListener studentViewListener;
		
		public StudentMainLayout(NavigationCrudListener studentViewListener) {
			this.studentViewListener = studentViewListener;
		}
		
		public StudentMainLayout init() {
			studentDto = new StudentDto();
			grid = new Grid<>(StudentDto.class, false);
			grid.addColumn(StudentDto::getFirstName).setHeader("First name");
			grid.addColumn(StudentDto::getLastName).setHeader("Last Name");
			grid.addColumn(StudentDto::getAge).setHeader("Age");
			grid.addColumn(StudentDto::getGender).setHeader( "Gender");
						
			buttonRemove = new Button("Remove");
			buttonEdit = new Button("Edit");
			buttonEdit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
			buttonEdit.addClickListener(event -> {
				if(Objects.isNull(studentDto) || Objects.isNull(studentDto.getId())) {
					Notification notification = Notification.show("Please select an Student");
					notification.setPosition(Notification.Position.MIDDLE);
					return;
				}
				
				this.studentViewListener.navigateNextPage(new ActionPage(0, studentDto));
				//this.studentViewListener.loadObject(studentDto);
			});
			buttonRemove.addClickListener(event ->{
				if(Objects.isNull(studentDto) || Objects.isNull(studentDto.getId())) {
					Notification notification = Notification.show("Please select an Student");
					notification.setPosition(Notification.Position.MIDDLE);
					return;
				}
				studentService.deleteById(studentDto);
				refresh();
			});
			grid.addSelectionListener(selection ->{
				Optional<StudentDto> optionalStudent = selection.getFirstSelectedItem();
				if(optionalStudent.isPresent()) {
					
					studentDto = optionalStudent.get();
				} else {
					studentDto = new StudentDto();
				
				}
			});
			grid.setItems(studentService.findAll());
			return this;
		}
		
		public void refresh() {
			grid.setVisible(true);
			grid.setItems(studentService.findAll());
			grid.getDataProvider().refreshAll();
		}
		
		public Component layout() {
			HorizontalLayout horizontaltudentListLayout = new HorizontalLayout();
			VerticalLayout verticalStudentListLayout = new VerticalLayout();
			verticalStudentListLayout.add(grid);
			verticalStudentListLayout.add(horizontaltudentListLayout);
			horizontaltudentListLayout.add(buttonEdit);
			horizontaltudentListLayout.add(buttonRemove);
			return verticalStudentListLayout;
		}
	}
	
	
	public Component createComponent(NavigationCrudListener studentViewListener) {
		studentMainLayout = new StudentMainLayout(studentViewListener).init();
		return studentMainLayout.layout();
	}
	
	@Override
	public void refresh() {
		studentMainLayout.refresh();
	}
}
