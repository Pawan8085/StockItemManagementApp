package com.dmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dmart.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByAdminId(String adminId);
	
	@Query("SELECT c FROM Category c JOIN c.dmartLocation d WHERE d.city = ?1")
	List<Category> getCategoryDetailsByCity(String city);


}
