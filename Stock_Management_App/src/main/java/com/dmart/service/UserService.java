package com.dmart.service;

import java.util.List;

import com.dmart.dto.UserRegisterDTO;
import com.dmart.entity.Category;
import com.dmart.entity.StockItem;
import com.dmart.exceptions.ItemException;

public interface UserService {
	
	/**
	 * 
	 * @param data -> user registration data
	 * @return -> registration confirmation message
	 */
	String registerUser(UserRegisterDTO data);
	
	
	/**
	 * 
	 * @param itemId -> item's id
	 * @param quantity -> item's quantity
	 * @return -> buy confirmation message
	 * @throws ItemException
	 */
	String buyItem(Long itemId, Long quantity)throws ItemException;
	
	
	/**
	 * 
	 * @return -> List of Category;
	 */
	List<Category> getAllCategory();
	
	
	/**
	 * 
	 * @return -> List of items
	 */
	List<StockItem> getAllItems();
}
