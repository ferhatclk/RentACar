package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.entities.concretes.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{
	Address findById(int id);
}
