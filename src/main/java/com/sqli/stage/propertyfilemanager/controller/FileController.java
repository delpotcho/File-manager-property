package com.sqli.stage.propertyfilemanager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sqli.stage.propertyfilemanager.dto.FolderDTO;
import com.sqli.stage.propertyfilemanager.entities.Folder;
import com.sqli.stage.propertyfilemanager.facade.ComparePropertieFileFacade;
import com.sqli.stage.propertyfilemanager.service.FolderService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FileController {
	@Autowired
	private FolderService folderService;

	@Autowired
	private ComparePropertieFileFacade comparePropertieFileFacade;

	@PostMapping("/uploadFileZip")
	@ApiOperation(value = " upload file zip  ", notes = "provide an file zip to compare variable from file ", response = Folder.class)
	public ResponseEntity<Object> uploadFileZip(@RequestParam("file") MultipartFile file) {

		String fileName = file.getOriginalFilename().toUpperCase();
		
			if (fileName.endsWith(".ZIP")) {
				Folder folder = comparePropertieFileFacade.comparePropertieFile(file);

				return new ResponseEntity<>(folder.getId(), HttpStatus.OK);
			

			} else {
				return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
		}
//
//		} catch (Exception e) {
//			return new ResponseEntity<Object>(HttpStatus.NOT_ACCEPTABLE);
//		}

	}

	@GetMapping("/file/{id}")
	@ApiOperation(value = "finds file by id ", notes = "provide an id to look up compare spesific file", response = Folder.class)
	public List<FolderDTO> getFileById(@PathVariable Long id) {
		return folderService.getFolderById(id).stream().map(file -> {
			return new FolderDTO(file);

		}).collect(Collectors.toList());

	}

}