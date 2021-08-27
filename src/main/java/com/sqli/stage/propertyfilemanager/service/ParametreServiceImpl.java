package com.sqli.stage.propertyfilemanager.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dto.ParametreRepository;
import com.sqli.stage.propertyfilemanager.entities.Parametre;

@Service
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

	@Override
	public List<Parametre> addAllParametres(Map<String, Properties> listFileProperties) {
		Set<String> listeParametreGlobale = new HashSet<String>();
		List<Parametre> listParametreEntities = new ArrayList<Parametre>();
		listFileProperties.values().forEach((K) -> {
			K.forEach((k, v) -> {
				listeParametreGlobale.add(k.toString());
			});

		});
		listeParametreGlobale.forEach((s) -> {
			Parametre parametre = new Parametre(0, s);
			parametreRepository.save(parametre);
			listParametreEntities.add(parametre);

		});

		return listParametreEntities;
	}
	public Parametre searchParametre(List<Parametre> pram, String keyParam) {
		Parametre parametreEntitie = new Parametre();
		for (Parametre par : pram) {

			if (par.getParametrekey().equals(keyParam)) {

				parametreEntitie = par;
			}
		}
		return parametreEntitie;

	}

}
