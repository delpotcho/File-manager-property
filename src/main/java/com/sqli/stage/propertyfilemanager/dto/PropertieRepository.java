package com.sqli.stage.propertyfilemanager.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.stage.propertyfilemanager.entities.Propertie;

public interface PropertieRepository extends JpaRepository<Propertie, Long> {

}
