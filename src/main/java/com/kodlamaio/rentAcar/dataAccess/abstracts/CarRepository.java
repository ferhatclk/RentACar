package com.kodlamaio.rentAcar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.entities.concretes.Car;

public interface CarRepository extends JpaRepository<Car, Integer>{
	List<Car> getByBrandId(int brandId);
	Car findById(int id);
}
