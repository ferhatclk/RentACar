package com.kodlamaio.rentAcar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "additionals")
public class Additional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@Column(name = "days")
	private long days;
	
	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;
	
	@ManyToOne
	@JoinColumn(name = "additional_item_id")
	private AdditionalItem additionalItem;
}
