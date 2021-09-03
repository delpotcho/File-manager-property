package com.sqli.stage.propertyfilemanager.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sqli.stage.propertyfilemanager.entities.Fichier;

public class FileDTO {

	private String name;
	private String type;
	private List<ValueDTO> property = new ArrayList<>();

	public FileDTO(Fichier propertie) {
		this.setName(propertie.getName());
		this.setType(propertie.getType());
		List<ValueDTO> valuesDto = propertie.getValues().stream().map(ValueDTO::new).collect(Collectors.toList());
		this.setProperty(valuesDto);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public List<ValueDTO> getProperty() {
		return property;
	}

	public void setProperty(List<ValueDTO> property) {
		this.property = property;
	}

}
