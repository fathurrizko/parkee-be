package com.parkee.pos.service;

import com.parkee.pos.entity.Pos;
import com.parkee.pos.repository.PosRepository;
import org.springframework.stereotype.Service;

@Service
public class PosService {

    private final PosRepository repo;

    public PosService(PosRepository repo) {
        this.repo = repo;
    }

    public Pos findByPosCode(String posCode) {
        System.out.println("service: " +posCode);
        return repo.findByPosCode(posCode);
    }

}
