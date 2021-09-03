package com.sqli.stage.propertyfilemanager.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity

public class Property {

	private String value;
	@JsonIgnore
	@EmbeddedId
	private PropertyId valueId = new PropertyId();

	@ManyToOne
	@MapsId("parametreId")
	@JoinColumn(name = "parametre_id")
	private Parametre parametre;

	@ManyToOne
	@MapsId("statusId")
	@JoinColumn(name = "status_id")
	private Status status;
	
	@JsonIgnore
	@ManyToOne
	@MapsId("fileId")
	@JoinColumn(name = "file_id")
	private Fichier file;

	

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PropertyId getValueId() {
		return valueId;
	}

	public void setValueId(PropertyId valueId) {
		this.valueId = valueId;
	}

	public Parametre getParametre() {
		return parametre;
	}

	public void setParametre(Parametre parametre) {
		this.parametre = parametre;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Fichier getPropertie() {
		return file;
	}

	public void setPropertie(Fichier propertie) {
		this.file = propertie;
	}

	public Property(String name, Parametre parametre, Status status, Fichier propertie) {
		super();
		this.value = name;

		this.parametre = parametre;
		this.status = status;
		this.file = propertie;
	}

	public Property() {
		super();
		// TODO Auto-generated constructor stub
	}

}
