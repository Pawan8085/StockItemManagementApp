package com.dmart.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;
	
	@NotEmpty(message = "Category name is required")
	private String categoryName;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String adminId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<StockItem> items;
	
	@ManyToOne
	@JsonBackReference
	private DmartLocation dmartLocation;
	
	
}
