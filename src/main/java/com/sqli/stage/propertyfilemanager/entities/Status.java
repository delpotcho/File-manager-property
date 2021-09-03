package com.sqli.stage.propertyfilemanager.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long statusId;
	
	private String type;
	@JsonIgnore
	@OneToMany(mappedBy = "status")
	private List<Property> values;

	public long getId() {
		return statusId;
	}

	public void setId(long id) {
		this.statusId = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public List<Property> getValues() {
		return values;
	}

	public void setValues(List<Property> values) {
		this.values = values;
	}

	public Status(long statusId, String type) {
		super();
		this.statusId = statusId;
		this.type = type;

	}

	public Status() {
		super();
		
	}
	

}
