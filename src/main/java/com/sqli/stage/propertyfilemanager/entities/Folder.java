package com.sqli.stage.propertyfilemanager.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Folder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private Date date;

	@OneToMany(mappedBy = "folder")
	private List<Fichier> file;

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

	public List<Fichier> getProperties() {
		return file;
	}

	public void setProperties(List<Fichier> properties) {
		this.file = properties;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Folder(long id, String name, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;

	}

	public Folder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Folder(long id, String name, Date date, List<Fichier> properties) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.file = properties;
	}

}
