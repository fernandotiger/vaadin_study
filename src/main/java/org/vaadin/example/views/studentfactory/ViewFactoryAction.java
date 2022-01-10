package org.vaadin.example.views.studentfactory;

import java.util.Optional;

import org.vaadin.example.model.dto.ModelDto;

public interface ViewFactoryAction {

	void refresh(Optional<ModelDto> modelDto) ;
}
