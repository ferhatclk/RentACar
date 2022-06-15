package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.entities.concretes.User;

public interface UserRespository extends JpaRepository<User, Integer>{
	User findByNationalIdentity(String nationalIdentity);
	User findById(int id);
}
