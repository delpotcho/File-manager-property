package com.sqli.stage.propertyfilemanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sqli.stage.propertyfilemanager.entities.Folder;
@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
	

}
