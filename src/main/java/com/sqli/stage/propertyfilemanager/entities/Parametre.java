package com.sqli.stage.propertyfilemanager.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Parametre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	private long parametreId;
	private String parametrekey;
	@OneToMany(mappedBy = "parametre")
	private List<Property> property;

	public long getId() {
		return parametreId;
	}

	public void setId(long id) {
		this.parametreId = id;
	}
	@JsonIgnore
	public List<Property> getValues() {
		return property;
	}

	public void setValues(List<Property> values) {
		this.property = values;
	}

	public String getParametrekey() {
		return parametrekey;
	}

	public void setParametrekey(String parametrekey) {
		this.parametrekey = parametrekey;
	}

	public Parametre() {
		super();
		
	}

	public Parametre(long parametreId, String parametrekey) {
		super();
		this.parametreId = parametreId;
		this.parametrekey = parametrekey;

	}

}
