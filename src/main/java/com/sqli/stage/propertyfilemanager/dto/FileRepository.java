package com.sqli.stage.propertyfilemanager.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.stage.propertyfilemanager.entities.File;

public interface FileRepository extends JpaRepository<File, Long> {
	

}
