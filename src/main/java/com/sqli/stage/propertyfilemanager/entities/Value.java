package com.sqli.stage.propertyfilemanager.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.springframework.beans.factory.annotation.Autowired;

@Entity

public class Value {

	private String name;
	@Autowired
	@EmbeddedId
	private ValueId valueId;

	@ManyToOne
	@MapsId("parametreId")
	@JoinColumn(name = "parametre_id")
	private Parametre parametre;

	@ManyToOne
	@MapsId("statusId")
	@JoinColumn(name = "status_id")
	private Status status;

	@ManyToOne
	@MapsId("propertieId")
	@JoinColumn(name = "propertie_id")
	private Propertie propertie;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ValueId getValueId() {
		return valueId;
	}

	public void setValueId(ValueId valueId) {
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

	public Propertie getPropertie() {
		return propertie;
	}

	public void setPropertie(Propertie propertie) {
		this.propertie = propertie;
	}

}
