package com.parkee.pos.controller;

import com.parkee.pos.dto.MemberResponse;
import com.parkee.pos.service.MemberService;
import com.parkee.pos.entity.Member;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @GetMapping("/vehicle/{vehicleNo}")
    public MemberResponse getMemberByVehicleNo(@PathVariable String vehicleNo) {
        return service.getByVehicleNo(vehicleNo);
    }
}
