package com.parkee.pos.service;

import com.parkee.pos.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import com.parkee.pos.entity.Vehicle;
import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repo;

    public VehicleService(VehicleRepository repo) {
        this.repo = repo;
    }

    public List<Vehicle> findAll() {
        return repo.findAll();
    }

    public Vehicle findById(String type) {
        return repo.findById(type).orElse(null);
    }
    
    public List<Vehicle> findActive() {
        return repo.findByStatus("A"); 
    }
}
