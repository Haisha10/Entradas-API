package com.upc.EntradasAPI.service;

import org.springframework.transaction.annotation.Transactional;

import com.upc.EntradasAPI.model.Sale;
import com.upc.EntradasAPI.repository.SaleRepository;

import java.util.List;

import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    // Leer todos los registros
    @Transactional(readOnly = true)
    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    // Leer un registro por id
    @Transactional(readOnly = true)
    public Sale getById(Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No se encontró el registro con id: " + id));
        
    }

    // Crear un registro
    @Transactional
    public Sale create(Sale sale) {
        return saleRepository.save(sale);
    }

    // Actualizar un registro
    @Transactional
    public Sale update(Long id, Sale sale) {
        Sale saleToUpdate = saleRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No se encontró el registro con id: " + id));
        if (saleToUpdate.equals(sale)) {
            return saleToUpdate;
        }
        saleToUpdate.setDate(sale.getDate());
        saleToUpdate.setTicket_id(sale.getTicket_id());
        saleToUpdate.setTicket_quantity(sale.getTicket_quantity());
        return saleRepository.save(saleToUpdate);
    }

    // Eliminar un registro
    @Transactional
    public Sale delete(Long id) {
        Sale saleToDelete = saleRepository.findById(id)
                .orElseThrow(() -> new OpenApiResourceNotFoundException("No se encontró el registro con id: " + id));
        saleRepository.delete(saleToDelete);
        return saleToDelete;
    }

}
