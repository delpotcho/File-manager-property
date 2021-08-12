package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sqli.stage.propertyfilemanager.dto.FileRepository;
import com.sqli.stage.propertyfilemanager.entities.File;

public class FilleServiceImpl implements FilleService {
	@Autowired
	 FileRepository fileRepository;

	@Override
	public List<File> getAllFille() {
		
		return null;
	}

	@Override
	public File getFileById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteFile(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createFile(File file) {
		fileRepository.save(file);
		
	}

	@Override
	public void updateFile(File file) {
		// TODO Auto-generated method stub
		
	}

}
