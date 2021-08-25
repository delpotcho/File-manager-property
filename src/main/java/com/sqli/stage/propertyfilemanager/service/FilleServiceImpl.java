package com.sqli.stage.propertyfilemanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.dto.FileRepository;
import com.sqli.stage.propertyfilemanager.entities.File;
@Service
public class FilleServiceImpl implements FilleService {
	@Autowired
	FileRepository fileRepository;

	@Override
	public List<File> getAllFille() {

		return fileRepository.findAll();
	}

	@Override
	public File getFileById(Long id) {

		return fileRepository.getById(id);
	}

	@Override
	public void deleteFile(File file) {
		fileRepository.delete(file);

	}

	@Override
	public void createFile(File file) {
		fileRepository.save(file);

	}
	

	@Override
	public void updateFile(File file) {
		fileRepository.save(file);

	}
	public File createAllFile(MultipartFile file) {
		File folder = new File(0, file.getOriginalFilename(), new Date());
		fileRepository.save(folder);
		return folder;

	}

}
