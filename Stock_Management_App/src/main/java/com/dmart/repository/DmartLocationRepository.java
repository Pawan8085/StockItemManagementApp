package com.dmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmart.entity.DmartLocation;

public interface DmartLocationRepository extends JpaRepository<DmartLocation, Long>{

}
