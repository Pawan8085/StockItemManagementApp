package com.dmart.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DmartLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dmartId;

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Location is required")
	private String location;

	@NotEmpty(message = "City is required")
	private String city;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Category> categories;

	@OneToOne
	@JsonBackReference
	private DmartAdmin dmartAdmin;
}
