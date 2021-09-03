package com.sqli.stage.propertyfilemanager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sqli.stage.propertyfilemanager.dao.StatusRepository;
import com.sqli.stage.propertyfilemanager.entities.Status;

@Service
public class StatusServiceImpl implements StatusService {
	@Autowired
	private StatusRepository statusRepository;

	@Override
	public List<Status> getAllStatus() {

		return statusRepository.findAll();
	}

	@Override
	public Status getStatusById(Long id) {

		return statusRepository.getById(id);
	}

	@Override
	public void deleteStatus(Status status) {

		statusRepository.delete(status);
	}

	@Override
	public void createStatus(Status status) {
		statusRepository.save(status);

	}

	@Override
	public void updateStatus(Status status) {
		statusRepository.save(status);

	}

	@Override
	public List<Status> addAllStatus() {
		List<Status> status = new ArrayList<Status>();
		Status statusDiff = new Status(0, "differant");
		createStatus(statusDiff);
		status.add(statusDiff);
		Status statusNormal = new Status(0, "normal");
		createStatus(statusNormal);
		status.add(statusNormal);
		Status statusOublie = new Status(0, "oublie");
		createStatus(statusOublie);
		status.add(statusOublie);
		return status;
	}

	@Override
	public Status searchStatus(List<Status> listStatus, String nameStatus) {
		Status status = null;
		for (Status s : listStatus) {
			if (s.getType().equals(nameStatus)) {
				status = s;
			}
		}

		return status;
	}

}
