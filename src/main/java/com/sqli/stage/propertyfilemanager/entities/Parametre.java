package com.sqli.stage.propertyfilemanager.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Parametre {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String parametrekey;
	@OneToMany(mappedBy = "parametre")
	private List<Value> values;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	public String getParametrekey() {
		return parametrekey;
	}

	public void setParametrekey(String parametrekey) {
		this.parametrekey = parametrekey;
	}

}
