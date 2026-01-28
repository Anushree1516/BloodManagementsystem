package com.example.bloodbank.service;

import com.example.bloodbank.model.BloodStock;
import com.example.bloodbank.repository.BloodStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodStockService {

    private final BloodStockRepository repository;

    public BloodStockService(BloodStockRepository repository) {
        this.repository = repository;
    }

    public BloodStock addStock(BloodStock stock) {
        return repository.save(stock);
    }

    public List<BloodStock> searchByGroup(String group) {
        return repository.findByBloodGroup(group);
    }

    public void deleteStock(Long id) {
        repository.deleteById(id);
    }
}
