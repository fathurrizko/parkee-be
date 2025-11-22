package com.parkee.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.parkee.pos.entity.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, String> {
    
}
