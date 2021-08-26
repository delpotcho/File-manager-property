package com.sqli.stage.propertyfilemanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dto.PropertieRepository;
import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.entities.Propertie;
@Service
public  class PropertieServiceImpl implements PropertieService {
	@Autowired
PropertieRepository propertieRepository;
	
	@Value("${propertie.commun.name}")
	private String nameFilePropertyCommun;

	@Override
	public List<Propertie> getAllPropertie() {

		return propertieRepository.findAll();
	}

	@Override
	public Propertie getPropertieById(Long id) {

		return propertieRepository.getById(id);
	}

	@Override
	public void deletePropertie(Propertie propertie) {
		propertieRepository.delete(propertie);
		
	}

	@Override
	public void addPropertie(Propertie propertie) {
  propertieRepository.save(propertie);
  
		
	}

	@Override
	public void updatePropertie(Propertie propertie) {
propertieRepository.save(propertie);
		
	}
	
	public void addPropertieCommun (Map<String, Properties> listFile, File folder) {
		listFile.forEach((k, V) -> {
			if (k.equals(nameFilePropertyCommun)) {

				Propertie propertieCommun = new Propertie(0, k, "Commun", folder);
				propertieRepository.save(propertieCommun);
			}
		});
	}

	@Override
	public List<Propertie> addAllPropertieSpec(Map<String, Properties> listFile, File folder) {

		List<Propertie> listePropertie = new ArrayList<Propertie>();
		listFile.forEach((k, v) -> {
			Propertie p = new Propertie(0, k, "spec", folder);
			propertieRepository.save(p);
			listePropertie.add(p);
		});
		return listePropertie;
	}

}
