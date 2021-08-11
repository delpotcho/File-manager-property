package com.sqli.stage.propertyfilemanager.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Value implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	@EmbeddedId
	@ManyToOne
	private Parametre parametre;
	@EmbeddedId
	@ManyToOne
	private Statut statut;
	@EmbeddedId
	@ManyToOne
	private Propertie propertie;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Statut getStatut() {
		return statut;
	}

	public void setStatut(Statut statut) {
		this.statut = statut;
	}

	public Propertie getPropertie() {
		return propertie;
	}

	public void setPropertie(Propertie propertie) {
		this.propertie = propertie;
	}

}
