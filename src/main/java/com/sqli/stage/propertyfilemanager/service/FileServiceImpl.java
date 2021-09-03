package com.sqli.stage.propertyfilemanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dao.FileRepository;
import com.sqli.stage.propertyfilemanager.entities.Fichier;
import com.sqli.stage.propertyfilemanager.entities.Folder;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileRepository fileRepository;

	@Value("${propertie.commun.name}")
	private String nameFilePropertyCommun;

	@Override
	public List<Fichier> getAllFile() {

		return fileRepository.findAll();
	}

	@Override
	public Fichier getFileById(Long id) {

		return fileRepository.getById(id);
	}

	@Override
	public void deleteFile(Fichier propertie) {
		fileRepository.delete(propertie);

	}

	@Override
	public void addFile(Fichier propertie) {
		fileRepository.save(propertie);

	}

	@Override
	public void updateFile(Fichier propertie) {
		fileRepository.save(propertie);

	}

	public void addFileCommun(Map<String, Properties> listFile, Folder folder) {
		listFile.forEach((k, v) -> {
			if (k.matches("(.*)" + nameFilePropertyCommun)) {

				Fichier propertieCommun = new Fichier(0, k, "Commun", folder);
				fileRepository.save(propertieCommun);
			}
		});
	}

	@Override
	public List<Fichier> addAllFileSpec(Map<String, Properties> listFile, Folder folder) {

		List<Fichier> listePropertie = new ArrayList<Fichier>();
		listFile.forEach((k, v) -> {

			Fichier p = new Fichier(0, k, "spec", folder);
			fileRepository.save(p);
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
