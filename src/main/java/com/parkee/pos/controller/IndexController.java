package com.parkee.pos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parkee.pos.service.ParkingService;
import com.parkee.pos.service.ConfigService;
import com.parkee.pos.entity.Config;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class IndexController {

    private final ParkingService parkingService;
    private final ConfigService configService;

    public IndexController(ParkingService parkingService, ConfigService configService) {
        this.parkingService = parkingService;
        this.configService = configService;
    }

    @GetMapping("/halo")
    public String halo() {
        return "Hello World!";
    }

    @GetMapping("/clock-in/{vehicleNo}")
    public ResponseEntity<?> getClockInTicket(@PathVariable String vehicleNo) {
        Map<String, Object> parking = parkingService.findClockInByVehicleNo(vehicleNo);
        return ResponseEntity.ok(parking);
    }

    @PostMapping("/clock-in/insert")
    public ResponseEntity<?> insertClockInTicket(@RequestBody Map<String, Object> body) {
        String vehicleNo = (String) body.get("vehicleNo");
        String vehicleType = (String) body.get("vehicleType");
        String posCode = (String) body.get("posCode");
        try {
            parkingService.insertClockIn(vehicleNo, vehicleType, posCode);
        } catch (Exception e) {
            System.err.println("Error 9001: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/config/{code}")
    public List<Config>getConfig(@PathVariable String code) {        
        return configService.getConfigByStatusAndCode("A", code);
    }
}
