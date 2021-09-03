package com.sqli.stage.propertyfilemanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.dao.FileRepository;
import com.sqli.stage.propertyfilemanager.entities.Folder;
@Service
public class FolderServiceImpl implements FolderService {
	@Autowired
	private FileRepository fileRepository;

	@Override
	
	public List<Folder> getAllFolders() {

		return fileRepository.findAll();
	}

	
	@Override
	public Optional<Folder> getFolderById(Long id) {

		return fileRepository.findById(id);
	}


	@Override
	public void deleteFolder(Folder folder) {
		fileRepository.delete(folder);

	}

	@Override
	public void addFolder(Folder file) {
		fileRepository.save(file);

	}
	


	@Override
	public void updateFolder(Folder file) {
		fileRepository.save(file);

	}
	
	@Override
	public Folder addAllFolders(MultipartFile file) {
		Folder folder = new Folder(0, file.getOriginalFilename(), new Date());
		fileRepository.save(folder);
		return folder;

	}

}
