package com.dmart.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StockItemMovement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long itemId;
	private Long leftItem;
	private Long soldItem;
	private Boolean isOutOfStock;
	private Long totalRevenue;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String adminId;

}
