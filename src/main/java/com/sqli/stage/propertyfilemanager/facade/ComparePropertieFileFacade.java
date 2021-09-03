package com.sqli.stage.propertyfilemanager.facade;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.compare.TraitementFile;
import com.sqli.stage.propertyfilemanager.entities.Folder;
import com.sqli.stage.propertyfilemanager.entities.Parametre;
import com.sqli.stage.propertyfilemanager.entities.Fichier;
import com.sqli.stage.propertyfilemanager.entities.Status;
import com.sqli.stage.propertyfilemanager.service.FolderService;
import com.sqli.stage.propertyfilemanager.service.ParametreService;
import com.sqli.stage.propertyfilemanager.service.FileService;
import com.sqli.stage.propertyfilemanager.service.StatusService;

@Component
public class ComparePropertieFileFacade {

	@Autowired
	private TraitementFile traitementFile;

	@Autowired
	private FileService fileService;

	@Autowired
	private ParametreService parametreService;

	@Autowired
	private FolderService folderService;
	@Autowired
	private StatusService statusService;

	public ComparePropertieFileFacade() {
		super();

	}

	public Folder comparePropertieFile(MultipartFile folders) {
		Folder folder = folderService.addAllFolders(folders);
		Map<String, Properties> filesProperty = null;
		try {
			filesProperty = traitementFile.scannedFile(folders);
			traitementFile.arshiveFileProperty(folders);
		} catch (IOException e) {

			e.printStackTrace();
		}
		fileService.addFileCommun(filesProperty, folder);

		Map<String, Properties> fileSpec = traitementFile.addProtertieCommunToSpec(filesProperty);

		List<Fichier> filez = fileService.addAllFileSpec(fileSpec, folder);

		List<Parametre> param = parametreService.addAllParametres(fileSpec);
		List<Status> status = statusService.addAllStatus();

				traitementFile.compareFile(fileSpec, filez, param, status);

		return folder;
	}

}
