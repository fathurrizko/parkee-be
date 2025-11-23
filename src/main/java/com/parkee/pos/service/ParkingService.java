package com.parkee.pos.service;

import java.util.Map;
import org.springframework.stereotype.Service;
import com.parkee.pos.repository.ParkingRepository;
import com.parkee.pos.entity.Parking;
import com.parkee.pos.repository.ParkingJdbcRepository;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ParkingService {
    private final ParkingRepository repo;
    private final ParkingJdbcRepository jdbc;

    public ParkingService(ParkingRepository repo, ParkingJdbcRepository jdbc) {
        this.repo = repo;
        this.jdbc = jdbc;
    }

    public Map<String, Object> findClockInByVehicleNo(String vehicleNo) {
        return jdbc.findClockInByVehicleNo(vehicleNo); 
    }
    
    public Map<String, Object> getClockOutTicket(String vehicleNo) {
        return jdbc.getParkingRates(vehicleNo); 
    }
    
    public Map<String, Object>saveClockOut(Map<String, Object> prm) {
        return jdbc.saveClockOut(prm); 
    }
    
    public Parking insertClockIn(String vehicleNo, String vehicleType, String posCode) {
        Parking parking = new Parking();
        LocalDateTime now = LocalDateTime.now();
        
        parking.setTransactionId(UUID.randomUUID().toString());
        parking.setVehicleNo(vehicleNo);
        parking.setVehicleType(vehicleType);
        parking.setPosCodeIn(posCode);
        parking.setClockIn(now);
        parking.setTransactionStatus("OPEN");
        parking.setDateCreate(now);
        parking.setUserCreate(posCode);
        parking.setDateUpd(now);
        parking.setUserUpd(posCode);

        return repo.save(parking);
    }
}
