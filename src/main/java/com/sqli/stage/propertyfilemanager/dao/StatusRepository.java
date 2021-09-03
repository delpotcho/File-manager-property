package com.sqli.stage.propertyfilemanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.stage.propertyfilemanager.entities.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
