package com.sqli.stage.propertyfilemanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.entities.File;

public interface FileService {
	public List<File> getAllFille();

	public Optional<File> getFileById(Long id);

	public void deleteFile(File file);

	public void addFile(File file);

	public void updateFile(File file);

	public File addAllFile(MultipartFile file);

}
