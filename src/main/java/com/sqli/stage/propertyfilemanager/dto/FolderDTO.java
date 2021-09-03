package com.sqli.stage.propertyfilemanager.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sqli.stage.propertyfilemanager.entities.Folder;

public class FolderDTO {

	private long id;
	private String name;
	private Date date;
	private List<FileDTO> file = new ArrayList<>();

	public FolderDTO(Folder folder) {
		this.setId(folder.getId());
		this.setDate(folder.getDate());
		this.setName(folder.getName());
		List<FileDTO> propertieDto = folder.getProperties().stream().map(FileDTO::new)
				.collect(Collectors.toList());
		this.setFile(propertieDto);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	

	public List<FileDTO> getFile() {
		return file;
	}

	public void setFile(List<FileDTO> file) {
		this.file = file;
	}

}
