package org.vaadin.example.views;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.vaadin.example.MainView;
import org.vaadin.example.StudentMainLayoutFactory;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.charts.model.Navigator;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

@Route(value = "university/student", layout = MainView.class)
public class StudentView extends VerticalLayout{

	private static final long serialVersionUID = -5512902352411892964L;

	private final Map<Tab, Component> contents = new LinkedHashMap<>();
	
	
	private StudentMainLayoutFactory studentMainLayoutFactory;
	
	public StudentView(@Autowired StudentMainLayoutFactory studentMainLayoutFactory) {
		this.studentMainLayoutFactory = studentMainLayoutFactory;
		this.buildContentAndTabs();
		Tabs tabs = new Tabs();
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
        final String[] data = new String[] {
            "Main",
            "All human beings are born free and equal in dignity and rights. They are endowed with reason and conscience and should act towards one another in a spirit of brotherhood.",
            "Show Students",
            "Show Students here !"
        };
        // create tabs and matching contents
        for (int zmp1 = 0; zmp1 < data.length; zmp1 += 2) this.contents.put(
                new Tab(data[zmp1]),
                new Span(studentMainLayoutFactory.createComponent())
            );
    }
	
}
