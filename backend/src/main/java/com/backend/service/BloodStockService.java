package com.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.BloodStock;
import com.backend.repo.BloodStockRepo;

	@Service
	public class BloodStockService {

	    @Autowired
	    private BloodStockRepo bloodStockRepository;

	    public List<BloodStock> getAllBloodStocks() {
	        return bloodStockRepository.findAll();
	    }

	    public Optional<BloodStock> getBloodStockById(Integer id) {
	        return bloodStockRepository.findById(id);
	    }

	    public BloodStock createBloodStock(BloodStock bloodStock) {
	        return bloodStockRepository.save(bloodStock);
	    }

	    public BloodStock updateBloodStock(Integer id, BloodStock bloodStockDetails) {
	        if (bloodStockRepository.existsById(id)) {
	            bloodStockDetails.setId(id);
	            return bloodStockRepository.save(bloodStockDetails);
	        }
	        return null; // or throw an exception
	    }

	    public boolean deleteBloodStock(Integer id) {
	        if (bloodStockRepository.existsById(id)) {
	            bloodStockRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	}



