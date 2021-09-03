package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import com.sqli.stage.propertyfilemanager.entities.Property;

public interface PropertyService {

	public List<Property> getAllProperty();

	public void deleteProperty(Property value);

	public void addProperty(Property value);

	public void updateProperty(Property value);
}
