package com.sqli.stage.propertyfilemanager.service;

import java.util.List;

import com.sqli.stage.propertyfilemanager.entities.Status;

public interface StatusService {
	public List<Status> getAllStatus();

	public Status getStatusById(Long id);

	public void deleteStatus(Status status);
	
	public void createStatus(Status status);
	
	public void updateStatus (Status status);
	
	
}
