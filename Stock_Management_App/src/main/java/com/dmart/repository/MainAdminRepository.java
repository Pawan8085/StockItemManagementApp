package com.dmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmart.entity.MainAdmin;

public interface MainAdminRepository extends JpaRepository<MainAdmin, Long> {
	
	Optional<MainAdmin> findByEmail(String mail);
}
