package com.dmart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dmart.dto.UserRegisterDTO;
import com.dmart.entity.Category;
import com.dmart.entity.DmartAdmin;
import com.dmart.entity.DmartLocation;
import com.dmart.entity.StockItem;
import com.dmart.entity.StockItemMovement;
import com.dmart.exceptions.CategoryException;
import com.dmart.exceptions.ItemException;
import com.dmart.repository.CategoryRepository;
import com.dmart.repository.DmartAdminRepository;
import com.dmart.repository.StockItemMovementRepository;
import com.dmart.repository.StockItemRepository;

@Service
public class DmartAdminServiceImpl implements DmartAdminService{
	
	@Autowired
	private DmartAdminRepository dmartAdminRepository;
	@Autowired
	private StockItemRepository stockItemRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired 
	private StockItemMovementRepository stockItemMovementRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	/**
	 * 
	 * @param data -> user registration data
	 * @return -> registration confirmation message
	 */
	@Override
	public String registerDmartAdmin(UserRegisterDTO data) {
		
		DmartAdmin admin = new DmartAdmin();
		
		admin.setCity(data.getCity());
		admin.setEmail(data.getEmail());
		admin.setName(data.getName());
		admin.setPassword(passwordEncoder.encode(data.getPassword()));
		admin.setState(data.getState());
		
		dmartAdminRepository.save(admin);
		
		return admin.getName()+" has registerd successfully";
	}
	
	
	/**
	 * 
	 * @param dmart -> Dmart location data
	 * @return -> confirmation message
	 */
	@Override
	public String createDmart(DmartLocation dmart) {
		// *Getting admin info from Authentication object
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String userName = authentication.getName();
			
			Optional<DmartAdmin> optAdmin = dmartAdminRepository.findByEmail(userName);
			
			// !we can avoid isPresent() method cause only authorized admin will enter here
			
			optAdmin.get().setDmart(dmart);
			
			return "Your Dmart store with the name '" + dmart.getName() + "' has been successfully created.";
		
		
	}


	
	/**
	 * 
	 * @param category -> tacks new category
	 * @return -> added category
	 */
	@Override
	public Category addCategory(Category category) {
		
		// *Getting admin info from Authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		category.setAdminId(userName);
		
		Optional<DmartAdmin> optDmartAdmin = dmartAdminRepository.findByEmail(userName);
		
		// *Adding category in dmart store
		optDmartAdmin.get().getDmart().getCategories().add(category);
		category.setDmartLocation(optDmartAdmin.get().getDmart());
		
		return categoryRepository.save(category);
		
	}
	
	
	/**
	 * 
	 * @param categoryId -> for finding category
	 * @param item -> for adding new item to category
	 * @return -> new added item in category
	 * @throws CategoryException
	 */
	@Override
	public StockItem addStockItem(Long categoryId, StockItem item) throws CategoryException {
		
		// *Getting admin info from Authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();	
		
		
		Optional<Category> optCategory = categoryRepository.findById(categoryId);
		
		if(optCategory.isPresent()) {
			
			
			Category category = optCategory.get();
			
			/*  -> there could be many Dmart Admin so admin may enter their categoryId but we have to add item in 
			 current admin's category so we need to check if category contains 'adminId' of  current admin then only we 
			 are supposed to add item in category.   below if condition is handling this situation
			  
			 */
			if(!category.getAdminId().equals(userName)) {
				
				throw new CategoryException("Invalid categoryId "+categoryId);
			}
			
			// *Adding item in category
			item.setAdminId(userName);
			category.getItems().add(item);
			item.setCategory(category);
			
		
			// *Creating and Updating new StockItemMovement 
			StockItemMovement itemMovement = new StockItemMovement();
			itemMovement.setAdminId(userName);
			itemMovement.setItemId(item.getItemId());
			itemMovement.setIsOutOfStock(item.getIsOutOfStock());
			itemMovement.setLeftItem(item.getQuantity());
			itemMovement.setSoldItem(0L);
			itemMovement.setTotalRevenue(0L);
			
			stockItemMovementRepository.save(itemMovement);
			
			return stockItemRepository.save(item);
			
			
		}
		throw new CategoryException("Invalid categoryId "+categoryId);
		
	}

	
	/**
	 * 
	 * @param itemId -> for finding item
	 * @param quantity -> for updating quantity of item
	 * @return -> updation message
	 * @throws ItemException
	 */
	@Override
	public String updateQuantity(Long itemId, Long quantity) throws ItemException {
		
		// *Getting admin info from Authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();			
				
		
		Optional<StockItem> optItem = stockItemRepository.findById(itemId);
		
		if(optItem.isPresent()) {
			
			
			
			if(quantity<1) {
				throw new ItemException("quantity must be positive number");
			}
			
			StockItem item = optItem.get();
			
			// * admin may enter other admin's itemId so checking itemId belongs to current admin's item or Not
			if(!item.getAdminId().equals(userName)) {
				throw new CategoryException("Invalid itemId "+itemId);
			}
			
			Long prevQuantity = item.getQuantity();
			item.setQuantity(item.getQuantity()+quantity);
			stockItemRepository.save(item);
			
			
			Optional<StockItemMovement> optStockItemMovement = stockItemMovementRepository.findByItemId(itemId);
			
			StockItemMovement stockItemMovement = optStockItemMovement.get();
			stockItemMovement.setLeftItem(item.getQuantity());
			stockItemMovementRepository.save(stockItemMovement);
			
			return item.getName()+" quantity updated "+ prevQuantity + " to "+item.getQuantity();
				
		}
		throw new ItemException("Invalid itemId "+itemId);
	}

	

	/**
	 * 
	 * @param itemId -> for finding item
	 * @return -> deleting regarding message
	 * @throws ItemException
	 */
	@Override
	public String deleteStockItem(Long itemId) throws ItemException {
		
		// *Getting admin info from Authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();	
		
		Optional<StockItem> optItem = stockItemRepository.findById(itemId);
		if(optItem.isPresent()) {
			
			StockItem item = optItem.get();
			
			// * Checking itemId belongs to current user or not
			if(!item.getAdminId().equals(userName)) {
				throw new CategoryException("Invalid itemId "+itemId);
			}
	
			stockItemRepository.delete(item);
			
			return "Item "+item.getName()+" deleted successfully";
			
		}
		throw new ItemException("Invalid itemId "+itemId);
	}

	
	/**
	 * 
	 * @return -> all items detailed information for a DmartLocation
	 */
	@Override
	public List<StockItemMovement> getStockItemMovement() {

		// *Getting admin info from Authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();	
		
		List<StockItemMovement> items = stockItemMovementRepository.findByAdminId(userName);
		
		return items;
	}

	
	/**
	 * 
	 * @return -> DmartLocation of current user 
	 */
	@Override
	public DmartLocation getCurrentAdminDmart() {
		// *Getting admin info from Authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();		
		
		
		Optional<DmartAdmin> optAdmin = dmartAdminRepository.findByEmail(userName);
		
		return optAdmin.get().getDmart();
	}


	
	

}
