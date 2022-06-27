package com.kodlamaio.rentAcar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "individual_customer_id", referencedColumnName = "customer_id")
@Table(name = "individual_customers")
public class IndividualCustomer extends Customer{
	
	@Column(name ="individual_customer_id", insertable = false, updatable = false)
	private int individualCustomerId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "national_identity")
	private String nationalIdentity;
	
	@Column(name = "birth_day")
	private int birthDay;
	
}
