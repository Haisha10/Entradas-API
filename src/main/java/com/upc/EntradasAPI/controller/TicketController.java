package com.upc.EntradasAPI.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.upc.EntradasAPI.model.Ticket;
import com.upc.EntradasAPI.service.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TicketController {
    
    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getAll() {
        return new ResponseEntity<List<Ticket>>(ticketService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable (value = "id") Long id) {
        return new ResponseEntity<Ticket>(ticketService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/tickets")
    public ResponseEntity<Ticket> create(@RequestBody Ticket ticket) {
        return new ResponseEntity<Ticket>(ticketService.create(ticket), HttpStatus.CREATED);
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<Ticket> update(@PathVariable (value = "id") Long id, @RequestBody Ticket ticket) {
        return new ResponseEntity<Ticket>(ticketService.update(id, ticket), HttpStatus.OK);
    }

    @DeleteMapping("/tickets/{id}")
    public ResponseEntity<Ticket> delete(@PathVariable (value = "id") Long id) {
        return new ResponseEntity<Ticket>(ticketService.delete(id), HttpStatus.NO_CONTENT);
    }
}
