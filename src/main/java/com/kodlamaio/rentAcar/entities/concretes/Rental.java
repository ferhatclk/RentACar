package com.kodlamaio.rentAcar.entities.concretes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
	@JoinColumn(name = "pickup_city_id")
	private City pickupCity;
	
	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City returnCity;
	
	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "rental")
	List<OrderedAdditionalItem> additionals;
	
	@OneToOne(mappedBy = "rental")
	private Invoice invoice;
	
}
