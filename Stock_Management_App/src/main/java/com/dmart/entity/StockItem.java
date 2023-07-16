package com.dmart.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long itemId;
	@NotEmpty(message = "Name is required")
    private String name;
    
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be a positive number")
    private Long quantity;
    
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be a positive number")
    private Long price;
    
    @NotNull(message = "isOutOfStock is required")
    private Boolean isOutOfStock;
    
    @NotEmpty(message = "Dmart location is required")
    private String dmartLocation;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String adminId;
	
	@ManyToOne
	@JsonBackReference
	private Category category;
	

}
