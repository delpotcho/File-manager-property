package com.sqli.stage.propertyfilemanager.facade;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.compare.TraitementFile;
import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.entities.Parametre;
import com.sqli.stage.propertyfilemanager.entities.Propertie;
import com.sqli.stage.propertyfilemanager.entities.Status;
import com.sqli.stage.propertyfilemanager.service.FileService;
import com.sqli.stage.propertyfilemanager.service.ParametreService;
import com.sqli.stage.propertyfilemanager.service.PropertieService;
import com.sqli.stage.propertyfilemanager.service.StatusService;

@Component
public class ComparePropertieFileFacade {

	@Autowired
	private TraitementFile traitementFile;

	@Autowired
	private PropertieService propertieService;

	@Autowired
	private ParametreService parametreService;

	@Autowired
	private FileService filleService;
	@Autowired
	StatusService statusService;

	public ComparePropertieFileFacade() {
		super();

	}

	public File comparePropertieFile(MultipartFile file) {
		File folder = filleService.addAllFiles(file);
		Map<String, Properties> filesProperty = null;
		try {
			filesProperty = traitementFile.scannedFile(file);
			traitementFile.arshiveFileProperty(file);
		} catch (IOException e) {

			e.printStackTrace();
		}
		propertieService.addPropertieCommun(filesProperty, folder);

		Map<String, Properties> fileSpec = traitementFile.addProtertieCommunToSpec(filesProperty);

		List<Propertie> prop = propertieService.addAllPropertieSpec(fileSpec, folder);

		List<Parametre> param = parametreService.addAllParametres(fileSpec);
		List<Status> status = statusService.addAllStatus();

				traitementFile.compareFile(fileSpec, prop, param, status);

		return folder;
	}

}
