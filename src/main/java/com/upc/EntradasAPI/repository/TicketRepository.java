package com.upc.EntradasAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upc.EntradasAPI.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
