package com.parkee.pos.repository;

import com.parkee.pos.dto.MemberResponse;
import com.parkee.pos.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query(value = """
        SELECT 
            m.member_id,
            m.vehicle_no,
            m.vehicle_type,
            m.member_name,
            m.start_date,
            m.expired_date,
            m.vehicle_unit,
            m.status,
            m.date_create,
            m.user_create,
            m.date_upd,
            m.user_upd,
            v.description AS vehicle_desc
        FROM M_MEMBER m
        LEFT JOIN (
            SELECT VEHICLE_TYPE, DESCRIPTION 
            FROM M_VEHICLE 
            WHERE status = 'A'
        ) v ON m.vehicle_type = v.vehicle_type
        WHERE m.vehicle_no = :vehicleNo
    """, nativeQuery = true)
    MemberResponse findByVehicleNo(String vehicleNo);

}
