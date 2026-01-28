package com.example.bloodbank.repository;

import com.example.bloodbank.model.BloodStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BloodStockRepository extends JpaRepository<BloodStock, Long> {
    List<BloodStock> findByBloodGroup(String bloodGroup);
}
