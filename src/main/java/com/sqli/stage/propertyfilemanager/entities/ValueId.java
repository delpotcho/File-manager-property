package com.sqli.stage.propertyfilemanager.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class ValueId implements Serializable {

	private static final long serialVersionUID = 1L;
	private long propertieId;
	private long parametreId;
	private long statusId;

	public long getProperieId() {
		return propertieId;
	}

	public void setProperieId(long properieId) {
		this.propertieId = properieId;
	}

	public long getParametreId() {
		return parametreId;
	}

	public void setParametreId(long parametreId) {
		this.parametreId = parametreId;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(parametreId, propertieId, statusId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ValueId other = (ValueId) obj;
		return parametreId == other.parametreId && propertieId == other.propertieId && statusId == other.statusId;
	}

	public ValueId(long properieId, long parametreId, long statusId) {
		super();
		this.propertieId = properieId;
		this.parametreId = parametreId;
		this.statusId = statusId;
	}

	public ValueId() {
		super();

	}

}
