package com.sqli.stage.propertyfilemanager.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.entities.Propertie;

public interface PropertieService {
	public List<Propertie> getAllPropertie();

	public Propertie getPropertieById(Long id);

	public void deletePropertie(Propertie propertie);

	public void addPropertie(Propertie propertie);

	public void updatePropertie(Propertie propertie);

	public List<Propertie> addAllPropertieSpec(Map<String, Properties> listFile, File folder);

	public void addPropertieCommun(Map<String, Properties> listFile, File folder);
	
	public Propertie searchPropertie(List<Propertie> prop, String keyPropertie);

}
