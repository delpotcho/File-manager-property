package com.sqli.stage.propertyfilemanager.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.dao.FolderRepository;
import com.sqli.stage.propertyfilemanager.entities.Folder;
@Service
public class FolderServiceImpl implements FolderService {
	@Autowired
	private FolderRepository folderRepository;

	@Override
	
	public List<Folder> getAllFolders() {

		return folderRepository.findAll();
	}

	
	@Override
	public Optional<Folder> getFolderById(Long id) {

		return folderRepository.findById(id);
	}


	@Override
	public void deleteFolder(Folder folder) {
		folderRepository.delete(folder);

	}

	@Override
	public void addFolder(Folder file) {
		folderRepository.save(file);

	}
	


	@Override
	public void updateFolder(Folder file) {
		folderRepository.save(file);

	}
	
	@Override
	public Folder addAllFolders(MultipartFile file) {
		Folder folder = new Folder(0, file.getOriginalFilename(), new Date());
		folderRepository.save(folder);
		return folder;

	}

}
