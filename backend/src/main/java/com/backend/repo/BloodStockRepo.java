package com.backend.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.BloodStock;

public interface BloodStockRepo extends JpaRepository<BloodStock,Integer>{

}
