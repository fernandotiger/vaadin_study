package org.vaadin.example.views;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.example.MainView;
import org.vaadin.example.model.dto.ActionPage;
import org.vaadin.example.views.studentfactory.StudentListLayoutFactory;
import org.vaadin.example.views.studentfactory.StudentMainLayoutFactory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;

@Route(value = "university/student", layout = MainView.class)
public class StudentView extends VerticalLayout implements StudentViewCrudListener{

	private static final long serialVersionUID = -5512902352411892964L;

	private final Map<Tab, Component> contents = new LinkedHashMap<>();
		
	private StudentMainLayoutFactory studentMainLayoutFactory;
	
	private StudentListLayoutFactory studentListLayoutFactory;
	
	private Tabs tabs;
	
	public StudentView(@Autowired StudentMainLayoutFactory studentMainLayoutFactory, @Autowired StudentListLayoutFactory studentListLayoutFactory) {
		this.studentMainLayoutFactory = studentMainLayoutFactory;
		this.studentListLayoutFactory = studentListLayoutFactory;
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
        this.contents.put(new Tab("Main"), new Span(studentMainLayoutFactory.createComponent(this)));
        this.contents.put(new Tab("Show Students"), new Span(studentListLayoutFactory.createComponent(this)));
    }
	
	@Override
	public void navigateNextPage(ActionPage actionPage) {
		tabs.setSelectedIndex(actionPage.getPageIndex());
		
		switch (actionPage.getPageIndex()) {
		case 0:
			actionPage.refreshPage(studentMainLayoutFactory);
			break;
		case 1:
			actionPage.refreshPage(studentListLayoutFactory);
			break;
		default:
			break;
		}
	}

	
}
