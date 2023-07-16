package com.dmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dmart.dto.UserRegisterDTO;
import com.dmart.entity.Category;
import com.dmart.entity.StockItem;
import com.dmart.service.UserService;

@RestController
@RequestMapping("/dmart")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/user/register")
	public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO data){
		
		String msg = userService.registerUser(data);
		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/user/buy/{itemId}/{quantity}")
	public ResponseEntity<String> buyItemHandler(@PathVariable Long itemId, @PathVariable Long quantity){
		
		String msg = userService.buyItem(itemId, quantity);
		return new ResponseEntity<String>(msg, HttpStatus.ACCEPTED);
		
	}
	
	
	@GetMapping("/user/categories")
	public ResponseEntity<List<Category>> getCategoryHandler(){
		
		List<Category> categories = userService.getAllCategory();
		return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
	}
	
	@GetMapping("/user/items")
	public ResponseEntity<List<StockItem>> getAllItemsHandler(){
		
		List<StockItem> items = userService.getAllItems();
		return new ResponseEntity<List<StockItem>>(items, HttpStatus.OK);
	}
	

}
