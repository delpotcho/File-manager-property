package com.sqli.stage.propertyfilemanager.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long statusId;
	private String type;
	@OneToMany(mappedBy = "status")
	private List<Value> values;

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
}
