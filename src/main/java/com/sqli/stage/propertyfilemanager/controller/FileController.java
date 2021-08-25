package com.sqli.stage.propertyfilemanager.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.sqli.stage.propertyfilemanager.compare.TraitementFile;
import com.sqli.stage.propertyfilemanager.dto.FileRepository;
import com.sqli.stage.propertyfilemanager.dto.PropertieRepository;
import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.entities.Parametre;
import com.sqli.stage.propertyfilemanager.entities.Propertie;
import com.sqli.stage.propertyfilemanager.service.FilleServiceImpl;
import com.sqli.stage.propertyfilemanager.service.PropertieServiceImpl;

@RestController
public class FileController {
	@Autowired
	TraitementFile traitementFile;
	@Autowired
	FileRepository fileRepository;
	@Autowired
	PropertieServiceImpl propertieServiceImpl;
	File folder;
	@Autowired
FilleServiceImpl filleServiceImpl;
	@PostMapping("/uploadFileZip")
	public Optional<File> uploadFilezip(@RequestParam("file") MultipartFile file) throws IOException {
		folder = filleServiceImpl.createAllFile(file);
		Map<String, Properties> filesProperty = traitementFile.scannedFile(file);
		propertieServiceImpl.createAllPropertie(filesProperty, folder);
		Map<String, Properties> fileSpec = traitementFile.addProtertieCommonToSpecifique(filesProperty);
		List<Propertie> prop = traitementFile.addPropertySpec(fileSpec, folder);
		List<Parametre> param = traitementFile.addParametre(fileSpec);
		traitementFile.compareFile(fileSpec, prop, param);
		return fileRepository.findById(folder.getId());

	}

	@GetMapping("/getfile")
	public Optional<File> getFile() {
		return fileRepository.findById(folder.getId());
	}

}
