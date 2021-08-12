package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import com.sqli.stage.propertyfilemanager.entities.File;

public interface FilleService {
	public List<File> getAllFille();

	public File getFileById(Long id);

	public void deleteFile(File file);
	
	public void createFile(File file);
	
	public void updateFile(File file);
	
	

}
