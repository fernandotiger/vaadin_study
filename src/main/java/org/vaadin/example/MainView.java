package org.vaadin.example;

import java.util.Objects;

import org.vaadin.example.views.course.CourseView;
import org.vaadin.example.views.student.StudentView;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route("university") 
@PageTitle("Home")
public class MainView extends AppLayout  { 

	//@Autowired
	//private UniversLogoLayoutFactory logoLayout;
	
	public MainView() {
	 
		DrawerToggle toggle = new DrawerToggle();

	    H1 title = new H1("MyApp");
	    title.getStyle()
	      .set("font-size", "var(--lumo-font-size-l)")
	      .set("margin", "0");

	    Tabs tabs = getTabs();
	    
	    addToDrawer(tabs);
	    addToNavbar(toggle, title);
	    setContent(new Label("My content"));
	}
  
	
	
	private Tabs getTabs() {
	    Tabs tabs = new Tabs();
	    tabs.add(
	    		createTab(VaadinIcon.USER_HEART, "Student", StudentView.class),
	    		createTab(VaadinIcon.PACKAGE, "Course", CourseView.class),
			      createTab(VaadinIcon.DASHBOARD, "Dashboard", null),
			      createTab(VaadinIcon.CART, "Orders", null),
			      
			      createTab(VaadinIcon.RECORDS, "Documents", null),
			      createTab(VaadinIcon.LIST, "Tasks", null),
			      createTab(VaadinIcon.CHART, "Analytics", null)
	    );
	    tabs.setOrientation(Tabs.Orientation.VERTICAL);
	    return tabs;
	}

	private Tab createTab(VaadinIcon viewIcon, String viewName, Class<? extends Component> clazz) {
	    Icon icon = viewIcon.create();
	    icon.getStyle()
	      .set("box-sizing", "border-box")
	      .set("margin-inline-end", "var(--lumo-space-m)")
	      .set("margin-inline-start", "var(--lumo-space-xs)")
	      .set("padding", "var(--lumo-space-xs)");

	    RouterLink link = new RouterLink();
	    link.add(icon, new Span(viewName));
	    // Demo has no routes
	    if(Objects.nonNull(clazz)) {
	    	link.setRoute(clazz);
	    }
	    link.setTabIndex(-1);

	    return new Tab(link);
	}
}