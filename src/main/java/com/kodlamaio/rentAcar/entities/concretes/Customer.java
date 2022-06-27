package com.kodlamaio.rentAcar.entities.concretes;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "customer_id", referencedColumnName = "id")
@Table(name = "customers")
public class Customer extends User{
	
	@Column(name ="customer_id", insertable = false, updatable = false)
	private int customerId;
	
	@Column(name = "customer_number")
	private String customerNumber;
	
	@OneToMany(mappedBy = "customer")
	private List<Address> addresses;
	
	@OneToMany(mappedBy = "customer")
	private List<Rental> rentals;
}
