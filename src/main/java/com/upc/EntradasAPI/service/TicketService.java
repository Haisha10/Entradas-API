package com.upc.EntradasAPI.service;

import org.springframework.transaction.annotation.Transactional;

import com.upc.EntradasAPI.model.Ticket;
import com.upc.EntradasAPI.repository.TicketRepository;

import java.util.List;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    // Leer todos los registros
    @Transactional(readOnly = true)
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    // Leer un registro por id
    @Transactional(readOnly = true)
    public Ticket getById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No se encontró el registro con id: " + id));
        
    }

    // Crear un registro
    @Transactional
    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    // Actualizar un registro
    @Transactional
    public Ticket update(Long id, Ticket ticket) {
        Ticket ticketToUpdate = ticketRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No se encontró el registro con id: " + id));
        if (ticketToUpdate.equals(ticket)) {
            return ticketToUpdate;
        }
        ticketToUpdate.setCategory(ticket.getCategory());
        ticketToUpdate.setPrice(ticket.getPrice());
        return ticketRepository.save(ticketToUpdate);
    }

    // Eliminar un registro
    @Transactional
    public Ticket delete(Long id) {
        Ticket ticketToDelete = ticketRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No se encontró el registro con id: " + id));
        ticketRepository.delete(ticketToDelete);
        return ticketToDelete;
    }
}
