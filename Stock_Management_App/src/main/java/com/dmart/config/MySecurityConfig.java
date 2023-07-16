package com.dmart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {
	
	@Bean
	public SecurityFilterChain masaiSecurityConfig(HttpSecurity http) throws Exception {
	
	
		http.authorizeHttpRequests( (auth)->auth
				.antMatchers("/dmart/mainAdmin/highest_user_city",
						"/dmart/mainAdmin/highest_user_state",
						"/dmart/mainAdmin/dmart/*",
						"/dmart/mainAdmin/totalRevenue",
						"/dmart/mainAdmin/allDmartLocation")
				.hasAuthority("MAIN_ADMIN")
				.antMatchers("/dmart/dmartAdmin/createDmart",
						"/dmart/dmartAdmin/addCategory",
						"/dmart/dmartAdmin/addStockItem/**",
						"/dmart/dmartAdmin/updatedItemQuantity/**",
						"/dmart/dmartAdmin/deleteItem/**",
						"/dmart/dmartAdmin/stockItemMovement",
						"/dmart/dmartAdmin/getMyMart")
				.hasAuthority("DMART_ADMIN")
				.antMatchers("/dmart/user/buy/**",
						"/dmart/user/categories",
						"/dmart/user/items")
				.hasAuthority("USER")
				.antMatchers("dmart/user/register", 
						"/dmart/dmartAdmin/register", 
						"/dmart/mainAdmin/register"
						)
				.permitAll()
				
		).csrf().disable()
		.httpBasic();
	
		return http.build();
   
	}
	
	
	

	@Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }

}
