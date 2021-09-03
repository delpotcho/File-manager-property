package com.sqli.stage.propertyfilemanager.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.sqli.stage.propertyfilemanager.entities.Folder;
import com.sqli.stage.propertyfilemanager.entities.Fichier;

public interface FileService {

	public List<Fichier> getAllFile();

	public Fichier getFileById(Long id);

	public void deleteFile(Fichier propertie);

	public void addFile(Fichier propertie);

	public void updateFile(Fichier propertie);

	public void addFileCommun(Map<String, Properties> listFile, Folder folder);

	public List<Fichier> addAllFileSpec(Map<String, Properties> listFile, Folder folder);

	public Fichier searchFile(List<Fichier> prop, String keyPropertie);

}
