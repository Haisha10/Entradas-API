package com.upc.EntradasAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upc.EntradasAPI.model.Sale;

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
