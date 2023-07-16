package com.dmart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dmart.dto.MaxUserCity;
import com.dmart.dto.MaxUserState;
import com.dmart.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	Optional<User> findByEmail(String mail);
	
	@Query("SELECT city, COUNT(city) as count FROM User GROUP BY city "
			+ "HAVING COUNT(city) = (SELECT MAX(COUNT(city)) FROM User GROUP BY city)")
	List<MaxUserCity> findCityWithMaxCount();
	
	@Query("SELECT state, COUNT(state) as count FROM User GROUP BY state "
			+ "HAVING COUNT(state) = (SELECT MAX(COUNT(state)) FROM User GROUP BY state)")
	List<MaxUserState> findStateWithMaxCount();

}
