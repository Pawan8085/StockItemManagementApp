package com.dmart.service;

import java.util.List;

import com.dmart.dto.UserRegisterDTO;
import com.dmart.entity.Category;
import com.dmart.entity.DmartLocation;
import com.dmart.entity.StockItem;
import com.dmart.entity.StockItemMovement;
import com.dmart.exceptions.CategoryException;
import com.dmart.exceptions.ItemException;

public interface DmartAdminService {
	
	/**
	 * 
	 * @param data -> user registration data
	 * @return -> registration confirmation message
	 */
	String registerDmartAdmin(UserRegisterDTO data);
	
	
	/**
	 * 
	 * @param dmart -> Dmart location data
	 * @return -> confirmation message
	 */
	String createDmart(DmartLocation dmart);
	
	
	/**
	 * 
	 * @param category -> tacks new category
	 * @return -> added category
	 */
	Category addCategory(Category category);
	
	
	/**
	 * 
	 * @param categoryId -> for finding category
	 * @param item -> for adding new item to category
	 * @return -> new added item in category
	 * @throws CategoryException
	 */
	StockItem addStockItem(Long categoryId, StockItem item)throws CategoryException;
	
	
	/**
	 * 
	 * @param itemId -> for finding item
	 * @param quantity -> for updating quantity of item
	 * @return -> updation message
	 * @throws ItemException
	 */
	String updateQuantity(Long itemId, Long quantity)throws ItemException;
	
	
	/**
	 * 
	 * @param itemId -> for finding item
	 * @return -> deleting regarding message
	 * @throws ItemException
	 */
	String deleteStockItem(Long itemId)throws ItemException;
	
	
	/**
	 * 
	 * @return -> all items detailed information for a DmartLocation
	 */
	List<StockItemMovement> getStockItemMovement(); 
	
	
	/**
	 * 
	 * @return -> DmartLocation of current user 
	 */
	DmartLocation getCurrentAdminDmart();
	
	
	
}
