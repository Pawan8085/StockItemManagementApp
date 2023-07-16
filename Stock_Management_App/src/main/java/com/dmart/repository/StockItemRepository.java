package com.dmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dmart.entity.StockItem;

public interface StockItemRepository extends JpaRepository<StockItem, Long>{

//	 @Query("SELECT s FROM StockItem s INNER JOIN Category c  on s.categoryId= c.categoryId "
//	 		+ "INNER JOIN DmartLocation d on d.dmartId=c.dmartid WHERE d.city = ?1")
	 @Query("SELECT s FROM StockItem s " +
		       "JOIN s.category c " +
		       "JOIN c.dmartLocation d " +
		       "WHERE d.city = ?1")
	  List<StockItem> findStockItemsByCity (String city);
}
