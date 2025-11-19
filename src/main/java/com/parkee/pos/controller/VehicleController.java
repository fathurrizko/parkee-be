package com.parkee.pos.controller;

import java.util.List;
import com.parkee.pos.service.VehicleService;
import com.parkee.pos.entity.Vehicle;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vehicle> getAll() {
        return service.findAll();
    }

    @GetMapping("/{type}")
    public Vehicle getOne(@PathVariable String type) {
        return service.findById(type);
    }

    @GetMapping("/active")
    public List<Vehicle> getActive() {
        return service.findActive();
    }
}
