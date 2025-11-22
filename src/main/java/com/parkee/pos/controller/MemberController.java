package com.parkee.pos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parkee.pos.dto.MemberResponse;
import com.parkee.pos.service.MemberService;
import com.parkee.pos.entity.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TransferQueue;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Object>> getMemberByVehicleNo(@PathVariable String vehicleNo) {
        MemberResponse result =  service.getByVehicleNo(vehicleNo);
        Map<String, Object> transformResult = new HashMap<>();
        if (result == null) {
            transformResult.put("isMember", Boolean.FALSE);
            return ResponseEntity.ok(transformResult);
        }
        transformResult.put("isMember", Boolean.TRUE);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> transmapper = mapper.convertValue(result, Map.class);
        transformResult.putAll(transmapper);
        return ResponseEntity.ok(transformResult);
    }
}
