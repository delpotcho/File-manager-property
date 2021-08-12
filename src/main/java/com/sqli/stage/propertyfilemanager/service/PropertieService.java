package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import com.sqli.stage.propertyfilemanager.entities.Propertie;

public interface PropertieService {
	public List<Propertie> getAllPropertie();

	public Propertie getPropertieById(Long id);

	public void deletePropertie(Propertie propertie);

	public void createPropertie(Propertie propertie);

	public void updatePropertie(Propertie propertie);

}
