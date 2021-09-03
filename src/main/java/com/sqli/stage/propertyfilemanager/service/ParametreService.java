package com.sqli.stage.propertyfilemanager.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sqli.stage.propertyfilemanager.entities.Parametre;

public interface ParametreService {
	public List<Parametre> getAllParametre();

	public Parametre getParametreById(Long id);

	public void deleteParametre(Parametre parametre);

	public void createParametre(Parametre parametre);

	public void updateParametre(Parametre parametre);

	public List<Parametre> addAllParametres(Map<String, Properties> listFileProperties);

	public Parametre searchParametre(List<Parametre> pram, String keyParam);

}
