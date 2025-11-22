package com.parkee.pos.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    public ParkingJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> findClockInByVehicleNo(String vehicleNo) {

        String sql = """
            SELECT VEHICLE_NO, POS_CODE_IN, CLOCK_IN
            FROM T_PARKING
            WHERE VEHICLE_NO = ? AND (TRANSACTION_STATUS = 'OPEN' OR CLOCK_OUT IS NULL)
        """;

        Map<String, Object> res = new HashMap<>();

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{ vehicleNo }, (rs, rowNum) -> {
                Map<String, Object> rt = new HashMap<>();
                rt.put("vehicleNo", rs.getString("VEHICLE_NO"));
                rt.put("posCodeIn", rs.getString("POS_CODE_IN"));
                rt.put("clockInDate", rs.getString("CLOCK_IN"));
                rt.put("isClockIn", Boolean.TRUE);
                return rt;
            });
        } catch (EmptyResultDataAccessException e) {
            res.put("isClockIn", Boolean.FALSE);
            return res;
        } catch (Exception e) {
            res.put("isClockIn", Boolean.FALSE);
            return res;
        }
    }
}
