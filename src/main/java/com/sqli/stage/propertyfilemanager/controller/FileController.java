package com.sqli.stage.propertyfilemanager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.entities.File;
import com.sqli.stage.propertyfilemanager.facade.ComparePropertieFileFacade;
import com.sqli.stage.propertyfilemanager.service.FileService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
public class FileController {
	@Autowired
	private FileService fileService;

	@Autowired
	private ComparePropertieFileFacade comparePropertieFileFacade;
	private File folder;

	@PostMapping("/uploadFileZip")
	@ApiOperation(value = " upload file zip  "
				, notes = "provide an file zip to compare variable from file ")
	public ResponseEntity<Object> uploadFileZip(@RequestParam("file") MultipartFile file) {

		String fileName = file.getOriginalFilename().toUpperCase();
		try {
			if (fileName.endsWith(".ZIP")) {
				folder = comparePropertieFileFacade.comparePropertieFile(file);

				return new ResponseEntity<>("fichier bien ajoute", HttpStatus.OK);

			} else {
				return new ResponseEntity<>("Ajouter fichier sous la forme '.zip' ", HttpStatus.METHOD_NOT_ALLOWED);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

	@GetMapping("/getfile")
	@ApiOperation(value = "look last  spesific file "
				, notes = ""
				, response = File.class)
	public Optional<File> getlastFile() {
		return fileService.getFileById(folder.getId());
	}

	@GetMapping("/getfile/{id}")
	@ApiOperation(value = "finds file by id "
				, notes = "provide an id to look up compare spesific file"
				, response = File.class)
	public Optional<File> getFileById(@PathVariable Long id) {
		return fileService.getFileById(id);

	}

}