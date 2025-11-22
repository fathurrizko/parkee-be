package com.parkee.pos.controller;

import com.parkee.pos.entity.Pos;
import com.parkee.pos.service.PosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pos")
public class PosController {

    private final PosService service;

    public PosController(PosService service) {
        this.service = service;
    }
    
    @GetMapping("/{posCode}")
    public Pos getpPosByPosCode(@PathVariable String posCode) {
        return service.findByPosCode(posCode);
    }

}
