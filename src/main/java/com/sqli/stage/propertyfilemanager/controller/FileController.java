package com.sqli.stage.propertyfilemanager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.facade.ComparePropertieFileFacade;
import com.sqli.stage.propertyfilemanager.service.FileService;

@RestController
public class FileController {
	@Autowired
	private FileService fileService;

	@Autowired
	private ComparePropertieFileFacade comparePropertieFileFacade;
	private File folder;

	@PostMapping("/uploadFileZip")
	public ResponseEntity<Object> uploadFilezip(@RequestParam("file") MultipartFile file) {

		String fileName = file.getOriginalFilename().toUpperCase();
		try {
			if (fileName.endsWith(".ZIP")) {
				folder = comparePropertieFileFacade.comparePropertieFile(file);

				return new ResponseEntity<>("file bien ajoute", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("ajouter file '.zip' ", HttpStatus.METHOD_NOT_ALLOWED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping("/getfile")
	public Optional<File> getFile() {
		return fileService.getFileById(folder.getId());

	}

}
