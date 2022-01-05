package org.vaadin.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.vaadin.example.utils.UniversLogoLayoutFactory;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;

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
	    addToNavbar(toggle, title);setContent(new Label("My content"));
	}
  
	
	
	private Tabs getTabs() {
	    Tabs tabs = new Tabs();
	    tabs.add(
	    		createTab(VaadinIcon.USER_HEART, "Student"),
	    		createTab(VaadinIcon.PACKAGE, "University"),
			      createTab(VaadinIcon.DASHBOARD, "Dashboard"),
			      createTab(VaadinIcon.CART, "Orders"),
			      
			      createTab(VaadinIcon.RECORDS, "Documents"),
			      createTab(VaadinIcon.LIST, "Tasks"),
			      createTab(VaadinIcon.CHART, "Analytics")
	    );
	    tabs.setOrientation(Tabs.Orientation.VERTICAL);
	    return tabs;
	}

	private Tab createTab(VaadinIcon viewIcon, String viewName) {
	    Icon icon = viewIcon.create();
	    icon.getStyle()
	      .set("box-sizing", "border-box")
	      .set("margin-inline-end", "var(--lumo-space-m)")
	      .set("margin-inline-start", "var(--lumo-space-xs)")
	      .set("padding", "var(--lumo-space-xs)");

	    RouterLink link = new RouterLink();
	    link.add(icon, new Span(viewName));
	    // Demo has no routes
	    // link.setRoute(viewClass.java);
	    link.setTabIndex(-1);

	    return new Tab(link);
	}
}