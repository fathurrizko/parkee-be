package com.parkee.pos.repository;

import com.parkee.pos.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, String>{
    List<Vehicle> findByStatus(String status);
}
