package com.sqli.stage.propertyfilemanager.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dto.PropertieRepository;
import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.entities.Propertie;
@Service
public class PropertieServiceImpl implements PropertieService {
	@Autowired
PropertieRepository propertieRepository;
	@org.springframework.beans.factory.annotation.Value("${propertie.commun.name}")
	private String nameFilePropertyCommun;

	@Override
	public List<Propertie> getAllPropertie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Propertie getPropertieById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePropertie(Propertie propertie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPropertie(Propertie propertie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePropertie(Propertie propertie) {
		// TODO Auto-generated method stub
		
	}
	public void createAllPropertie (Map<String, Properties> listFile, File folder) {
		listFile.forEach((k, V) -> {
			if (k.equals(nameFilePropertyCommun)) {

				Propertie propertieCommun = new Propertie(0, k, "Commun", folder);
				propertieRepository.save(propertieCommun);
			}
		});
	}

}
