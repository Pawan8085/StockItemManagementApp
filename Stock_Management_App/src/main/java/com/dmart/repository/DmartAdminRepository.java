package com.dmart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dmart.entity.DmartAdmin;

public interface DmartAdminRepository extends JpaRepository<DmartAdmin, Long>{

	Optional<DmartAdmin> findByEmail(String mail);
}
