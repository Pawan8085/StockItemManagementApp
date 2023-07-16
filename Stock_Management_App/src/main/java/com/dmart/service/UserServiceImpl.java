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
import com.dmart.entity.StockItem;
import com.dmart.entity.StockItemMovement;
import com.dmart.entity.User;
import com.dmart.exceptions.ItemException;
import com.dmart.repository.CategoryRepository;
import com.dmart.repository.StockItemMovementRepository;
import com.dmart.repository.StockItemRepository;
import com.dmart.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private StockItemRepository stockItemRepo;
	@Autowired
	private CategoryRepository categorRepository;
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
	public String registerUser(UserRegisterDTO data) {
		User user = new User();
		user.setCity(data.getCity());
		user.setEmail(data.getEmail());
		user.setName(data.getName());
		user.setPassword(passwordEncoder.encode(data.getPassword()));
		user.setState(data.getState());
		
		userRepo.save(user);
		
		return user.getName()+" has registered successfully";
	}

	
	/**
	 * 
	 * @param itemId -> item's id
	 * @param quantity -> item's quantity
	 * @return -> buy confirmation message
	 * @throws ItemException
	 */
	@Override
	public String buyItem(Long itemId, Long quantity) throws ItemException {
		
		Optional<StockItem> optItem = stockItemRepo.findById(itemId);
		
		if(optItem.isPresent()) {
			
			if(quantity<1) {
				throw new ItemException("Item quantity must be positive");
			}
			
			StockItem item = optItem.get();
			
			// *Handling product out of stock 
			if(item.getIsOutOfStock()) {
				
				throw new ItemException("Item out of stock!");
			}
			
			// *Handling situation where user may enter quantity more than available item
			else if(item.getQuantity()-quantity<0){
				
				throw new ItemException("You can only by "+item.getQuantity()+" Item");
			}
			
			else {
				// *Updating item quantity
				item.setQuantity(item.getQuantity()-quantity);
				
				// *Checking and updating is item out of stock or not
				if(item.getQuantity()==0) {
					item.setIsOutOfStock(true);
				}
				
				
				// *Updating stock information in StockItemMovement
				Optional<StockItemMovement> optStockItem = stockItemMovementRepository.findByItemId(itemId);
				
				if(optStockItem.isPresent()) {
					StockItemMovement stockItemMovement = optStockItem.get();
					
					stockItemMovement.setIsOutOfStock(item.getIsOutOfStock());
					stockItemMovement.setLeftItem(item.getQuantity());
					stockItemMovement.setSoldItem(stockItemMovement.getSoldItem()+quantity);
					Long revenue = item.getPrice()*quantity;
					stockItemMovement.setTotalRevenue(stockItemMovement.getTotalRevenue()+revenue);
					
				}
				
				stockItemRepo.save(item);
				
				return "You are item will be delevird soon";
				
				
			}
		}
		throw new ItemException("Invalid item id "+itemId);
	}

	
	/**
	 * 
	 * @return -> List of Category;
	 */
	@Override
	public List<Category> getAllCategory() {
		
		// *Getting user city from Authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Optional<User> optUser = userRepo.findByEmail(userName);
		
		
		// *filtering based on current  user's city
		String city = optUser.get().getCity();
		
		List<Category> categories = categorRepository.getCategoryDetailsByCity(city);
		
		return categories;
	}

	

	/**
	 * 
	 * @return -> List of items
	 */
	@Override
	public List<StockItem> getAllItems() {
		
		// *Getting user city from Authentication object
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Optional<User> optUser = userRepo.findByEmail(userName);	
		
		// *filtering based on current  user's city
		String city = optUser.get().getCity();
			
		List<StockItem> items = stockItemRepo.findStockItemsByCity(city);
		
		
		return items;
	}

}
