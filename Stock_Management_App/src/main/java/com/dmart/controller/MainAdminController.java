package com.dmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.dto.MaxUserCity;
import com.dmart.dto.MaxUserState;
import com.dmart.dto.UserRegisterDTO;
import com.dmart.entity.DmartLocation;
import com.dmart.service.MainAdminService;

@RestController
@RequestMapping("/dmart")
public class MainAdminController {
	
	@Autowired
	private MainAdminService mainAdminService;
	
	
	@PostMapping("/mainAdmin/register")
	public ResponseEntity<String> registerAdmin(@RequestBody UserRegisterDTO data){
		
		String msg = mainAdminService.registerMainAdmin(data);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/mainAdmin/highest_user_city")
	public ResponseEntity<List<MaxUserCity>> highestUserCityHandler(){
		
		List<MaxUserCity> cities = mainAdminService.highestUserCity();
		return new ResponseEntity<List<MaxUserCity>>(cities, HttpStatus.OK);
		
	}
	
	@GetMapping("/mainAdmin/highest_user_state")
	public ResponseEntity<List<MaxUserState>> highestUserStateHandler(){
		
		List<MaxUserState> states = mainAdminService.highestUserState();
		return new ResponseEntity<List<MaxUserState>>(states, HttpStatus.OK);
		
	}
	
	@GetMapping("/mainAdmin/dmart/{dmartId}")
	public ResponseEntity<DmartLocation> getDmartLocationByIdHandler(@PathVariable Long dmartId){
		
		DmartLocation dmart = mainAdminService.getDmartLocationById(dmartId);
		return new ResponseEntity<DmartLocation>(dmart, HttpStatus.OK);
		
	}
	
	@GetMapping("/mainAdmin/totalRevenue")
	public ResponseEntity<Long> getTotalRevenueHandler(){
		
		Long revenue = mainAdminService.totalRevenue();
		return new ResponseEntity<Long>(revenue, HttpStatus.OK);
		
	}
	
	@GetMapping("/mainAdmin/allDmartLocation")
	public ResponseEntity<List<DmartLocation>> getAllDemartLocation(){
		
		List<DmartLocation> dmarts = mainAdminService.getAllDemartLocation();
		return new ResponseEntity<List<DmartLocation>>(dmarts, HttpStatus.OK);
		
	}
}
