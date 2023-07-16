package com.dmart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dmart.entity.StockItemMovement;

public interface StockItemMovementRepository extends JpaRepository<StockItemMovement, Long>{
	
	Optional<StockItemMovement> findByItemId(Long id);
	
	List<StockItemMovement> findByAdminId(String id);
	
	@Query("SELECT SUM(m.totalRevenue) FROM StockItemMovement m")
	Long getTotalRevenueSum();
}
