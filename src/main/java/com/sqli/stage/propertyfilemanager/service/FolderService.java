package com.sqli.stage.propertyfilemanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.entities.Folder;

public interface FolderService {

	public List<Folder> getAllFolders();

	public Optional<Folder> getFolderById(Long id);

	public Folder addAllFolders(MultipartFile file);

	public void updateFolder(Folder file);

	public void addFolder(Folder file);

	public void deleteFolder(Folder file);

}
