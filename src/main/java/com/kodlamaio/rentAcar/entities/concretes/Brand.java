package com.kodlamaio.rentAcar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler","cars"})
//eager loading 
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity 
@Table(name="brands")
public class Brand {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private int id;
	
	@Column(name = "name")
	private String name; 
	
	@OneToMany(mappedBy = "brand")
	List<Car> cars;
	
}
