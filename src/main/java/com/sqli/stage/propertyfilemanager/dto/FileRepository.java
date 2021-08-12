package com.sqli.stage.propertyfilemanager.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqli.stage.propertyfilemanager.entities.File;
@Repository
public interface FileRepository extends JpaRepository<File, Long> {
	

}
