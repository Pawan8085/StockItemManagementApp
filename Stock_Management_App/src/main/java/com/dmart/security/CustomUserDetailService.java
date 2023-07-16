package com.dmart.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dmart.entity.DmartAdmin;
import com.dmart.entity.MainAdmin;
import com.dmart.entity.User;
import com.dmart.repository.DmartAdminRepository;
import com.dmart.repository.MainAdminRepository;
import com.dmart.repository.UserRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired 
	private DmartAdminRepository dmartAdminRepository;
	
	@Autowired
	private MainAdminRepository mainAdminRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		Optional<User> optUser = userRepository.findByEmail(username);
		
		if(optUser.isPresent()) {
			
			User user = optUser.get();
			return new CustomeUserDetail(user);
			
		}
		Optional<DmartAdmin> optDmartAdmin = dmartAdminRepository.findByEmail(username);
		
	    if(optDmartAdmin.isPresent()) {
	    	
	    	DmartAdmin dmartAdmin= optDmartAdmin.get();
			return new CustomDmartAdminDetail(dmartAdmin);
		}
		
	    Optional<MainAdmin> optMainAdmin = mainAdminRepository.findByEmail(username);
	    
	    if(optMainAdmin.isPresent()) {
	    	
	    	MainAdmin mainAdmin = optMainAdmin.get();
	    	return new CustomMainAdminDetail(mainAdmin);
	    }
		else throw new UsernameNotFoundException("Invalid User email!");
		
		
	}

}
