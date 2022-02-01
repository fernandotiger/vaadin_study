package org.vaadin.example.views.course;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.MainView;
import org.vaadin.example.model.dto.ActionPage;
import org.vaadin.example.views.NavigationCrudListener;
import org.vaadin.example.views.course.factory.CourseListLayoutFactory;
import org.vaadin.example.views.course.factory.CourseMainLayoutFactory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Route(value = "university/course", layout = MainView.class)
public class CourseView  extends VerticalLayout implements NavigationCrudListener{

	private static final long serialVersionUID = 385803135203621915L;
	
	private final Map<Tab, Component> contents = new LinkedHashMap<>();
	
	private Tabs tabs;
	
	private CourseMainLayoutFactory courseMainLayoutFactory;
	
	private CourseListLayoutFactory courseListLayoutFactory;
	
	public CourseView(@Autowired CourseMainLayoutFactory courseMainLayoutFactory, @Autowired CourseListLayoutFactory courseListLayoutFactory) {
		this.courseMainLayoutFactory = courseMainLayoutFactory;
		this.courseListLayoutFactory = courseListLayoutFactory;
		this.buildContentAndTabs();
		tabs = new Tabs();
		// display area
        final Div display = new Div();
        display.setSizeFull();
        
		// update display area whenever tab selection changes
        tabs.addSelectedChangeListener(
            event -> {
                // remove old contents, if there was a previously selected tab
                if (event.getPreviousTab() != null) display.remove(this.contents.get(event.getPreviousTab()));
                // add new contents, if there is a currently selected tab
                if (event.getSelectedTab() != null) display.add(this.contents.get(event.getSelectedTab()));
            }
        );
        tabs.add(this.contents.keySet().toArray(new Tab[0]));
        
        add(tabs, display);
	}
	

	private void buildContentAndTabs() {
        // create tabs and matching contents
        this.contents.put(new Tab("Main"), new Span(courseMainLayoutFactory.createComponent(this)));
        this.contents.put(new Tab("Show Courses"), new Span(courseListLayoutFactory.createComponent(this)));
    }
	
	@Override
	public void navigateNextPage(ActionPage actionPage) {
		tabs.setSelectedIndex(actionPage.getPageIndex());
		
		switch (actionPage.getPageIndex()) {
		case 0:
			courseMainLayoutFactory.refresh(Optional.ofNullable(actionPage.getDtoObject()));;
			break;
		case 1:
			courseListLayoutFactory.refresh();
			break;
		default:
			break;
		}
		
	}

}
