package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sqli.stage.propertyfilemanager.dto.ParametreRepository;
import com.sqli.stage.propertyfilemanager.entities.Parametre;


public class ParametreServiceImpl implements ParametreService{
	@Autowired
ParametreRepository parametreRepository;
	
	@Override
	public List<Parametre> getAllParametre() {
		
		return parametreRepository.findAll(); 
	}

	@Override
	public Parametre getParametreById(Long id) {
		
		return parametreRepository.getById(id);
	}

	@Override
	public void deleteParametre(Parametre parametre) {
		parametreRepository.delete(parametre);
	}

	@Override
	public void createParametre(Parametre parametre) {
	parametreRepository.save(parametre);
		
	}

	@Override
	public void updateParametre(Parametre parametre) {
		parametreRepository.save(parametre);
	}

}
