package com.sqli.stage.propertyfilemanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.stage.propertyfilemanager.entities.Property;
import com.sqli.stage.propertyfilemanager.entities.PropertyId;

public interface ValueRepository extends JpaRepository<Property, PropertyId> {

}
