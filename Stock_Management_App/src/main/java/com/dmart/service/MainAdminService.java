package com.dmart.service;

import java.util.List;

import com.dmart.dto.MaxUserCity;
import com.dmart.dto.MaxUserState;
import com.dmart.dto.UserRegisterDTO;
import com.dmart.entity.DmartLocation;
import com.dmart.exceptions.DmartLocationException;

public interface MainAdminService {
	
	/**
	 * 
	 * @param data -> main admin registration data
	 * @return -> registration confirmation message
	 */
	String registerMainAdmin(UserRegisterDTO data);
	
	
	/**
	 * 
	 * @return ->List of MaxUserCity 
	 */
	List<MaxUserCity> highestUserCity();
	
	
	/**
	 * 
	 * @return -> List of MaxUserState
	 */
	List<MaxUserState> highestUserState();
	
	
	/**
	 * 
	 * @param dmartId -> for finding DmartLocation
	 * @return -> DmartLocation
	 */
	DmartLocation getDmartLocationById(Long dmartId)throws DmartLocationException;
	
	
	/**
	 * 
	 * @return -> total revenue of all dmart
	 */
	Long totalRevenue();
	
	
	/**
	 * 
	 * @return -> all DmartLocation
	 */
	List<DmartLocation> getAllDemartLocation();
	
	
	

}
