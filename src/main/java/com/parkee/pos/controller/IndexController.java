package com.parkee.pos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/api/halo")
    public String halo() {
        return "Hello World!";
    }
}
