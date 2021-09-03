package com.sqli.stage.propertyfilemanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.stage.propertyfilemanager.entities.Fichier;

public interface FileRepository extends JpaRepository<Fichier, Long> {

}
