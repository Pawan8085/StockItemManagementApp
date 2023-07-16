package com.dmart.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class DmartAdmin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long adminId;
	private String name;
	private String city;
	private String state;
	private String email;
	private String password;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonManagedReference
	private DmartLocation dmart;
	
	public DmartAdmin() {
		this.role = "DMART_ADMIN";
	}
}
