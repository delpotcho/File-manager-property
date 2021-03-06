package com.sqli.stage.propertyfilemanager.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class PropertyId implements Serializable {

	private static final long serialVersionUID = 1L;
	private long fileId;
	private long parametreId;
	private long statusId;

	public long getProperieId() {
		return fileId;
	}

	public void setProperieId(long properieId) {
		this.fileId = properieId;
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
		return Objects.hash(parametreId, fileId, statusId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PropertyId other = (PropertyId) obj;
		return parametreId == other.parametreId && fileId == other.fileId && statusId == other.statusId;
	}

	public PropertyId(long properieId, long parametreId, long statusId) {
		super();
		this.fileId = properieId;
		this.parametreId = parametreId;
		this.statusId = statusId;
	}

	public PropertyId() {
		super();

	}

}
