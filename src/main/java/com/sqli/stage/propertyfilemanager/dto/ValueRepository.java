package com.sqli.stage.propertyfilemanager.dto;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sqli.stage.propertyfilemanager.entities.Value;
import com.sqli.stage.propertyfilemanager.entities.ValueId;

public interface ValueRepository extends JpaRepository<Value, ValueId> {

}
