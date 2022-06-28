package com.kodlamaio.rentAcar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	
//	@Column(name = "first_name")
//	private String firstName;
//	
//	@Column(name = "last_name")
//	private String lastName;
//	
//	@Column(name = "national_identity")
//	private String nationalIdentity;
//	
//	@Column(name = "birth_day")
//	private int birthDay;
	
	
//	@OneToMany(mappedBy = "user")
//	private List<Address> addresses;
//	
//	@OneToMany(mappedBy = "user")
//	private List<Rental> rentals;
}
