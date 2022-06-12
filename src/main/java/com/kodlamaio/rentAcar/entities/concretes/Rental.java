package com.kodlamaio.rentAcar.entities.concretes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cars"})
@Table(name = "rentals")
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "pickup_date")
	private Date pickupDate;
	
	@Column(name = "return_date")
	private Date returnDate;
	
	@Column(name = "total_days")
	private long totalDays;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "pickUpCityId")
	private City pickCity;
	
	@ManyToOne
	@JoinColumn(name = "returnCityId")
	private City returnCity;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
}
