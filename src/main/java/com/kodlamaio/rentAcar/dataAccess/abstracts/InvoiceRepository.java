package com.kodlamaio.rentAcar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentAcar.entities.concretes.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer>{
	Invoice findById(int id); 
}
