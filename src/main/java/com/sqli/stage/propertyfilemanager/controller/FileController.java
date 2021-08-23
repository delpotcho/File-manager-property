package com.sqli.stage.propertyfilemanager.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.compare.TraitementFile;
import com.sqli.stage.propertyfilemanager.dto.FileRepository;

@RestController
public class FileController {
	@Autowired
	TraitementFile traitementFile;
	@Autowired
	FileRepository fileRepository;

	@PostMapping("/uploadFileZip")
	public  ResponseEntity<Object> uploadFilezip(@RequestParam("file") MultipartFile file) throws IOException {

		Map<String, List<String>> scannerFiles = traitementFile.scannedFile(file);

		Map<String, Map<String, String>> splitParametresFiles = traitementFile.splitListe(scannerFiles);
		Map<String, Map<String, String>> specificFiles = traitementFile.addProtertieCommonToSpecifique(splitParametresFiles);
		traitementFile.compareFileProperties(specificFiles);
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

}
