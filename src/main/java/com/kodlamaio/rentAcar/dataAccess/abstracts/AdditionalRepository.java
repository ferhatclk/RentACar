package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.entities.concretes.OrderedAdditionalItem;

public interface AdditionalRepository extends JpaRepository<OrderedAdditionalItem, Integer>{
	OrderedAdditionalItem findById(int id);
}
