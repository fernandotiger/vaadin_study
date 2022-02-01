package org.vaadin.example.views;

import java.util.Optional;

import org.vaadin.example.model.dto.ModelDto;

public interface ReloadFormAction {

	void refresh(Optional<ModelDto> modelDto) ;
}
