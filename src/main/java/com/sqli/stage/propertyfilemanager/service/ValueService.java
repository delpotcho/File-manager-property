package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import com.sqli.stage.propertyfilemanager.entities.Value;

public interface ValueService {
	public List<Value> getAllvalue();


	public void deleteValue(Value value);

	public void addValue(Value value);

	public void updateValue(Value value);
}
