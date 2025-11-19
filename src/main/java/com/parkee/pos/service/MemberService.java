package com.parkee.pos.service;

import com.parkee.pos.dto.MemberResponse;
import com.parkee.pos.repository.MemberRepository;
import org.springframework.stereotype.Service;
import com.parkee.pos.entity.Member;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository repo;

    public MemberService(MemberRepository repo) {
        this.repo = repo;
    }

    public MemberResponse getByVehicleNo(String vehicleNo) {
        return repo.findByVehicleNo(vehicleNo);
    }
}
