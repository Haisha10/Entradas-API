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
import java.util.List;


import com.upc.EntradasAPI.model.Sale;
import com.upc.EntradasAPI.service.SaleService;

@RestController
@RequestMapping("/api")
public class SaleController {
    
    @Autowired
    private SaleService saleService;

    @GetMapping("/sales")
    public ResponseEntity<List<Sale>> getAll() {
        return new ResponseEntity<List<Sale>>(saleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/sales/{id}")
    public ResponseEntity<Sale> getById(@PathVariable (value = "id") Long id) {
        return new ResponseEntity<Sale>(saleService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/sales")
    public ResponseEntity<Sale> create(@RequestBody Sale sale) {
        return new ResponseEntity<Sale>(saleService.create(sale), HttpStatus.CREATED);
    }

    @PutMapping("/sales/{id}")
    public ResponseEntity<Sale> update(@PathVariable (value = "id") Long id, @RequestBody Sale sale) {
        return new ResponseEntity<Sale>(saleService.update(id, sale), HttpStatus.OK);
    }

    @DeleteMapping("/sales/{id}")
    public ResponseEntity<Sale> delete(@PathVariable (value = "id") Long id) {
        return new ResponseEntity<Sale>(saleService.delete(id), HttpStatus.NO_CONTENT);
    }
}
