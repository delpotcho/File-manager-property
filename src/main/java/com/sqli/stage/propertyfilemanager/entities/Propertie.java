package com.sqli.stage.propertyfilemanager.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Propertie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long properieId;
	private String name;

	@ManyToOne
	private File file;
	@OneToMany(mappedBy = "propertie")
	private List<Value> values;

	public long getId() {
		return properieId;
	}

	public void setId(long id) {
		this.properieId = id;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

}
