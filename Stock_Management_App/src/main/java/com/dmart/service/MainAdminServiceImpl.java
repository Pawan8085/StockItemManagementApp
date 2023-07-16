package com.dmart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dmart.dto.MaxUserCity;
import com.dmart.dto.MaxUserState;
import com.dmart.dto.UserRegisterDTO;
import com.dmart.entity.DmartLocation;
import com.dmart.entity.MainAdmin;
import com.dmart.exceptions.DmartLocationException;
import com.dmart.repository.DmartLocationRepository;
import com.dmart.repository.MainAdminRepository;
import com.dmart.repository.StockItemMovementRepository;
import com.dmart.repository.UserRepository;

@Service
public class MainAdminServiceImpl implements MainAdminService{
	
	@Autowired
	private MainAdminRepository mainAdminRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private DmartLocationRepository dmartLocationRepository;
	@Autowired
	private StockItemMovementRepository stockItemMovementRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * 
	 * @param data -> main admin registration data
	 * @return -> registration confirmation message
	 */
	@Override
	public String registerMainAdmin(UserRegisterDTO data) {
		
		MainAdmin admin = new MainAdmin();
		admin.setCity(data.getCity());
		admin.setEmail(data.getEmail());
		admin.setName(data.getName());
		admin.setPassword(passwordEncoder.encode(data.getPassword()));
		admin.setState(data.getState());
		
		mainAdminRepository.save(admin);
		
		return admin.getName()+" has registered successfully";
		
	}

	
	/**
	 * 
	 * @return ->List of MaxUserCity 
	 */
	@Override
	public List<MaxUserCity> highestUserCity() {
		
		return userRepository.findCityWithMaxCount();
	}
	
	
	/**
	 * 
	 * @return -> List of MaxUserState
	 */
	@Override
	public List<MaxUserState> highestUserState() {
		
		return userRepository.findStateWithMaxCount();
	}
	
	
	/**
	 * 
	 * @param dmartId -> for finding DmartLocation
	 * @return -> DmartLocation
	 */
	@Override
	public DmartLocation getDmartLocationById(Long dmartId) throws DmartLocationException {
		
		Optional<DmartLocation> optDmartLocation = dmartLocationRepository.findById(dmartId);
		
		if(optDmartLocation.isPresent()) {
			return  optDmartLocation.get();
		}
		throw new DmartLocationException("Invalid dmartId "+dmartId);
	}

	
	/**
	 * 
	 * @return -> total revenue of all dmart
	 */
	@Override
	public Long totalRevenue() {
		
		return stockItemMovementRepository.getTotalRevenueSum();
	}

	
	/**
	 * 
	 * @return -> all DmartLocation
	 */
	@Override
	public List<DmartLocation> getAllDemartLocation() {
		
		return dmartLocationRepository.findAll();
	}

	

}
