package com.sqli.stage.propertyfilemanager.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Fichier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long properieId;
	private String name;
	private String type;
	@JsonIgnore
	@ManyToOne
	private Folder file;
	@OneToMany(mappedBy = "propertie")
	private List<Property> values;

	public long getId() {
		return properieId;
	}

	public void setId(long id) {
		this.properieId = id;
	}

	public Folder getFile() {
		return file;
	}

	public void setFile(Folder file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Property> getValues() {
		return values;
	}

	public void setValues(List<Property> values) {
		this.values = values;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Fichier(long properieId, String name, String type, Folder file) {
		super();
		this.properieId = properieId;
		this.name = name;
		this.type = type;
		this.file = file;
	
	}

	public Fichier() {
		super();
		
	}
	

}
