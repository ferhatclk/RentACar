package com.kodlamaio.rentAcar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "daily_price")
	private double dailyPrice;
	
	@Column(name = "plate")
	private String plate;
	
	@Column(name = "km")
	private int km;
	
	@Column(name = "min_findex")
	private int findexScore;
	
	@Column(name = "state")
	private int state;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	
	@OneToMany(mappedBy = "car")
	List<Maintenance> maintenances;
	
	@OneToMany(mappedBy = "car")
	List<Rental> rentals;
	

	
}
