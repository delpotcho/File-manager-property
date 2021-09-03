
package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dao.PropertyRepository;
import com.sqli.stage.propertyfilemanager.entities.Property;
@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	private PropertyRepository propertyRepository;

	
	@Override
	public List<Property> getAllProperty() {

		return propertyRepository.findAll();
	}


	@Override
	public void deleteProperty(Property value) {
		propertyRepository.delete(value);
		

	}

	
	@Override
	public void addProperty(Property value) {
		propertyRepository.save(value);

	}


	@Override
	public void updateProperty(Property value) {
		propertyRepository.save(value);
	}

}
