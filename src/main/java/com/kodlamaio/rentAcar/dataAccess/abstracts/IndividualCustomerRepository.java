package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.entities.concretes.IndividualCustomer;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer, Integer>{
	IndividualCustomer findById(int id);
	IndividualCustomer findByNationalIdentity(String nationalIdentity);
}
