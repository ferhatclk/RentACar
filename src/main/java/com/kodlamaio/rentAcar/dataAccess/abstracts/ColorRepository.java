package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;


import com.kodlamaio.rentAcar.entities.concretes.Color;

public interface ColorRepository extends JpaRepository<Color, Integer>{
	Color findById(int id);
}
