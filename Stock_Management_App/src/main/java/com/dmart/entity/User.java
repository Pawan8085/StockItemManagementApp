package com.dmart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User {
	
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
	
	public User() {
		this.role="USER";
	}
}
