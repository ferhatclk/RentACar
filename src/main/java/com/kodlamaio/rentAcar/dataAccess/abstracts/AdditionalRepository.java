package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.entities.concretes.Additional;

public interface AdditionalRepository extends JpaRepository<Additional, Integer>{
	Additional findById(int id);
}
