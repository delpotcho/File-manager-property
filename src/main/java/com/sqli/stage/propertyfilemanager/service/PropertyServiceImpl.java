
package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dao.ValueRepository;
import com.sqli.stage.propertyfilemanager.entities.Property;
@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	private ValueRepository valueRepository;

	
	@Override
	public List<Property> getAllProperty() {

		return valueRepository.findAll();
	}


	@Override
	public void deleteProperty(Property value) {
		valueRepository.delete(value);
		

	}

	
	@Override
	public void addProperty(Property value) {
		valueRepository.save(value);

	}


	@Override
	public void updateProperty(Property value) {
		valueRepository.save(value);
	}

}
