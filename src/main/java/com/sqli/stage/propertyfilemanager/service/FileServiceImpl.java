package com.sqli.stage.propertyfilemanager.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dao.PropertieRepository;
import com.sqli.stage.propertyfilemanager.entities.Folder;
import com.sqli.stage.propertyfilemanager.entities.Fichier;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private PropertieRepository propertieRepository;

	@Value("${propertie.commun.name}")
	private String nameFilePropertyCommun;


	@Override
	public List<Fichier> getAllFile() {

		return propertieRepository.findAll();
	}

	
	@Override
	public Fichier getFileById(Long id) {

		return propertieRepository.getById(id);
	}

	
	@Override
	public void deleteFile(Fichier propertie) {
		propertieRepository.delete(propertie);

	}

	
	@Override
	public void addFile(Fichier propertie) {
		propertieRepository.save(propertie);

	}

	
	@Override
	public void updateFile(Fichier propertie) {
		propertieRepository.save(propertie);

	}

	public void addFileCommun(Map<String, Properties> listFile, Folder folder) {
		listFile.forEach((k, v) -> {
			if (k.matches("(.*)"+nameFilePropertyCommun)) {

				Fichier propertieCommun = new Fichier(0, k, "Commun", folder);
				propertieRepository.save(propertieCommun);
			}
		});
	}


	@Override
	public List<Fichier> addAllFileSpec(Map<String, Properties> listFile, Folder folder) {

		List<Fichier> listePropertie = new ArrayList<Fichier>();
		listFile.forEach((k, v) -> {
		
			Fichier p = new Fichier(0, k, "spec", folder);
			propertieRepository.save(p);
			listePropertie.add(p);
		});
		return listePropertie;
	}


	@Override
	public Fichier searchFile(List<Fichier> prop, String keyPropertie) {
		Fichier propertieEntitie = new Fichier();
		for (Fichier p : prop) {

			if (p.getName().equals(keyPropertie)) {

				propertieEntitie = p;
			}
		}
		return propertieEntitie;

	}

}
