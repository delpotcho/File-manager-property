package com.sqli.stage.propertyfilemanager.dto;

import com.sqli.stage.propertyfilemanager.entities.Property;

public class ValueDTO {
	private String key;
	private String value;

	private String status;

	public ValueDTO(Property property) {
		this.setKey(property.getParametre().getParametrekey());
		this.setValue(property.getValue());
		this.setStatus(property.getStatus().getType());

	}

	

	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public String getValue() {
		return value;
	}



	public void setValue(String value) {
		this.value = value;
	}



	public String getKey() {
		return key;
	}



	public void setKey(String key) {
		this.key = key;
	}

}
