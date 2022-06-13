package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.entities.concretes.AdditionalItem;

public interface AdditionalItemRepository extends JpaRepository<AdditionalItem, Integer>{
	AdditionalItem findById(int id);
}
