package com.dmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.dto.UserRegisterDTO;
import com.dmart.entity.Category;
import com.dmart.entity.DmartLocation;
import com.dmart.entity.StockItem;
import com.dmart.entity.StockItemMovement;
import com.dmart.service.DmartAdminService;

@RestController
@RequestMapping("/dmart")
public class DmartAdmainController {
	
	@Autowired
	private DmartAdminService dmartAdminService;
	
	
	@PostMapping("/dmartAdmin/register")
	public ResponseEntity<String> registerAdmin(@RequestBody UserRegisterDTO data){
		
		String msg = dmartAdminService.registerDmartAdmin(data);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
	
	@PostMapping("/dmartAdmin/createDmart")
	public ResponseEntity<String> createDmartHandler(@RequestBody DmartLocation dmart){
		
		String msg = dmartAdminService.createDmart(dmart);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}
	
	@PostMapping("/dmartAdmin/addCategory")
	public ResponseEntity<Category> addCategoryHandler(@RequestBody Category category){
		
		Category addedCategory = dmartAdminService.addCategory(category);
		return new ResponseEntity<Category>(addedCategory, HttpStatus.CREATED);
	}
	
	@PostMapping("/dmartAdmin/addStockItem/{categoryId}")
	public ResponseEntity<StockItem> addStockItemHandler(@RequestBody StockItem item, @PathVariable Long categoryId){
		
		StockItem addedItem = dmartAdminService.addStockItem(categoryId, item);
		return new ResponseEntity<StockItem>(addedItem, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/dmartAdmin/updatedItemQuantity/{itemId}/{quantity}")
	public ResponseEntity<String> updateStockItemQuantityHandler(@PathVariable Long itemId, @PathVariable Long quantity){
		
		String msg = dmartAdminService.updateQuantity(itemId, quantity);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/dmartAdmin/deleteItem/{itemId}")
	public ResponseEntity<String> deleteStockItem(@PathVariable Long itemId){
		
		String msg = dmartAdminService.deleteStockItem(itemId);
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}
	
	@GetMapping("/dmartAdmin/stockItemMovement")
	public ResponseEntity<List<StockItemMovement>> stockItemMovementHandler(){
		
		List<StockItemMovement> items = dmartAdminService.getStockItemMovement();
		return new ResponseEntity<List<StockItemMovement>>(items, HttpStatus.OK);
		
	}
	
	@GetMapping("/dmartAdmin/getMyMart")
	public ResponseEntity<DmartLocation> getMyMartHandler(){
		
		DmartLocation dmart = dmartAdminService.getCurrentAdminDmart();
		return new ResponseEntity<DmartLocation>(dmart, HttpStatus.OK);
		
	}
}
