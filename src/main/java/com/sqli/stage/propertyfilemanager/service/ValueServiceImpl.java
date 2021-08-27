package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dto.ValueRepository;
import com.sqli.stage.propertyfilemanager.entities.Value;
@Service
public class ValueServiceImpl implements ValueService {
	@Autowired
	public ValueRepository valueRepository;

	@Override
	public List<Value> getAllvalue() {

		return valueRepository.findAll();
	}

	@Override
	public void deleteValue(Value value) {
		valueRepository.delete(value);
		

	}

	@Override
	public void addValue(Value value) {
		valueRepository.save(value);

	}

	@Override
	public void updateValue(Value value) {
		valueRepository.save(value);
	}

}
