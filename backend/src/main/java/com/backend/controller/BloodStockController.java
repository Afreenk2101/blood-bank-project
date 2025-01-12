package com.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.model.BloodStock;
import com.backend.service.BloodStockService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bloodstock")
public class BloodStockController {

    @Autowired
    private BloodStockService bloodStockService;

    // Create blood stock
    @PostMapping
    public ResponseEntity<BloodStock> createBloodStock(@RequestBody BloodStock bloodStock) {
        BloodStock createdBloodStock = bloodStockService.createBloodStock(bloodStock);
        return new ResponseEntity<>(createdBloodStock, HttpStatus.CREATED);
    }

    // Get all blood stocks
    @GetMapping
    public ResponseEntity<List<BloodStock>> getAllBloodStocks() {
        List<BloodStock> bloodStocks = bloodStockService.getAllBloodStocks();
        return new ResponseEntity<>(bloodStocks, HttpStatus.OK);
    }

    // Get a blood stock by ID
    @GetMapping("/{id}")
    public ResponseEntity<BloodStock> getBloodStockById(@PathVariable Integer id) {
        Optional<BloodStock> bloodStock = bloodStockService.getBloodStockById(id);
        return bloodStock.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update blood stock
    @PutMapping("/{id}")
    public ResponseEntity<BloodStock> updateBloodStock(@PathVariable Integer id, @RequestBody BloodStock bloodStockDetails) {
        BloodStock updatedBloodStock = bloodStockService.updateBloodStock(id, bloodStockDetails);
        return updatedBloodStock != null ? ResponseEntity.ok(updatedBloodStock) : ResponseEntity.notFound().build();
    }

    // Delete blood stock
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBloodStock(@PathVariable Integer id) {
        return bloodStockService.deleteBloodStock(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}




