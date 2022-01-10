package org.vaadin.example.model.dto;

import java.util.Optional;

import org.vaadin.example.views.studentfactory.ViewFactoryAction;

public class ActionPage {

	private int pageIndex;
	
	private ModelDto dtoObject;

	public ActionPage(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public ActionPage(int pageIndex, ModelDto dtoObject) {
		this.pageIndex = pageIndex;
		this.dtoObject = dtoObject;
	}
	
	public void refreshPage(ViewFactoryAction viewFactoryAction) {
		viewFactoryAction.refresh(Optional.ofNullable(dtoObject));
	}
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public ModelDto getDtoObject() {
		return dtoObject;
	}

	public void setDtoObject(ModelDto dtoObject) {
		this.dtoObject = dtoObject;
	}
	
	
}
