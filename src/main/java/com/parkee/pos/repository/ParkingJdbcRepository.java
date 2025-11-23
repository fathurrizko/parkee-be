package com.parkee.pos.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
// import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingJdbcRepository {

    // private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ParkingJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> getParkingRates(String vehicleNo) {
        String sql = """
                    SELECT fin.*,
                        CEIL(fin.total_minutes / fin.calculate_per_min) * FIN.RATES AS BILL_AMOUNT,
                        FLOOR(fin.total_minutes / 60) AS hours,
                        FLOOR(MOD(fin.total_minutes, 60)) AS minutes,
                        ROUND((fin.total_minutes - FLOOR(fin.total_minutes)) * 60) AS seconds
                        FROM (
                    SELECT TRANSACTION_ID, VEHICLE_NO, main.VEHICLE_TYPE, V.DESCRIPTION VEHICLE_DESCRIPTION, POS_CODE_IN, LOOKUP.PRICE_ID, CLOCK_IN, SYSDATE AS CLOCK_OUT, (SYSDATE - clock_in) * 24 * 60 AS total_minutes, lookup.calculate_per_min, lookup.rates
                        FROM t_parking main
                        LEFT JOIN (
                            SELECT POS_CODE, PR.*
                            FROM M_POS mp
                            LEFT JOIN (SELECT PRICE_ID, VEHICLE_TYPE, DURATION_MINUTE calculate_per_min, RATES FROM M_PRICE mp WHERE status = 'A' AND "TYPE" = 'REGULAR') pr ON mp.PRICE_ID = pr.PRICE_ID
                            WHERE MP.STATUS = 'A'
                        ) lookup ON main.POS_CODE_IN = lookup.pos_code AND main.VEHICLE_TYPE = lookup.vehicle_type AND main.VEHICLE_TYPE = lookup.vehicle_type
                        LEFT JOIN (SELECT VEHICLE_TYPE, DESCRIPTION FROM M_VEHICLE mv WHERE STATUS = 'A') V ON main.VEHICLE_TYPE = V.VEHICLE_TYPE
                        WHERE VEHICLE_NO = :vehicleNo AND (TRANSACTION_STATUS = 'OPEN' OR CLOCK_OUT IS NULL)
                    ) fin
                """;
        Map<String, Object> res = new HashMap<>();

        try {
            Map<String, Object> p = new HashMap<>();
            p.put("vehicleNo", vehicleNo);

            return jdbcTemplate.queryForObject(sql, p, (rs, rowNum) -> {
                Map<String, Object> bill = new HashMap<>();
                bill.put("billAmount", rs.getInt("BILL_AMOUNT"));
                bill.put("hours", rs.getInt("HOURS"));
                bill.put("minutes", rs.getInt("MINUTES"));
                bill.put("seconds", rs.getInt("SECONDS"));

                Map<String, Object> data = new HashMap<>();
                data.put("transactionId", rs.getString("TRANSACTION_ID"));
                data.put("vehicleNo", rs.getString("VEHICLE_NO"));
                data.put("vehicleType", rs.getString("VEHICLE_TYPE"));
                data.put("vehicleDescription", rs.getString("VEHICLE_DESCRIPTION"));
                data.put("posCodeIn", rs.getString("POS_CODE_IN"));
                data.put("priceId", rs.getString("PRICE_ID"));
                data.put("clockIn", rs.getString("CLOCK_IN"));
                data.put("clockOut", rs.getString("CLOCK_OUT"));
                // data.put("totalMinutes", rs.getString("TOTAL_MINUTES"));
                data.put("calculatePerMin", rs.getInt("CALCULATE_PER_MIN"));
                data.put("rates", rs.getInt("RATES"));
                data.put("billDetail", bill);

                Map<String, Object> rt = new HashMap<>();
                rt.put("success", Boolean.TRUE);
                rt.put("message", "ok");
                rt.put("data", data);
                return rt;
            });
        } catch (EmptyResultDataAccessException e) {
            res.put("success", Boolean.FALSE);
            res.put("message", "Kendaraan tidak terdaftar pada system");
            return res;
        } catch (Exception e) {
            res.put("success", Boolean.FALSE);
            res.put("message", "Gagal mendapatkan data kendaraan. Error 9002");
            System.err.println(new Date() + " - Error 9002: " + e.getMessage());
            return res;
        }
    }

    public Map<String, Object> saveClockOut(Map<String, Object> prm) {
        Map<String, Object> res = new HashMap<>();

        String query = """
                UPDATE T_PARKING
                    SET CLOCK_OUT = SYSDATE, POS_CODE_OUT = :posCode, PAYMENT_METHOD = :paymentMethod,
                    RATES_AMOUNT = :ratesAmount, DICSOUNT_AMOUNT = :discountAmount, SALES_AMOUNT = (:ratesAmount - :discountAmount),
                    TRANSACTION_STATUS = 'CLOSE', USER_UPD = :posCode, DATE_UPD = SYSDATE
                    WHERE TRANSACTION_id = :transactionId AND vehicle_no = :vehicleNo
                """;
        try {

            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("clockOut", prm.get("clockOut").toString())
                    .addValue("posCode", prm.get("posCode"))
                    .addValue("paymentMethod", prm.get("paymentMethod"))
                    .addValue("ratesAmount", Integer.parseInt(prm.get("ratesAmount").toString()))
                    .addValue("discountAmount", Integer.parseInt(prm.get("discountAmount").toString()))
                    .addValue("transactionId", prm.get("transactionId"))
                    .addValue("vehicleNo", prm.get("vehicleNo"));
            System.out.println("prm: " + params);
            jdbcTemplate.update(query, params);

            res.put("success", Boolean.TRUE);
            res.put("message", "OK");
            return res;

        } catch (Exception e) {
            res.put("success", Boolean.FALSE);
            res.put("message", "Gagal mendapatkan tarif data kendaraan. Error 9003");
            System.err.println(new Date() + " - Error 9003: " + e.getMessage());
            e.printStackTrace();
            return res;
        }
    }

    public Map<String, Object> findClockInByVehicleNo(String vehicleNo) {

        String sql = """
                    SELECT VEHICLE_NO, POS_CODE_IN, CLOCK_IN
                    FROM T_PARKING
                    WHERE VEHICLE_NO = :vehicleNo AND (TRANSACTION_STATUS = 'OPEN' OR CLOCK_OUT IS NULL) AND ROWNUM = 1
                """;

        Map<String, Object> res = new HashMap<>();

        try {
            Map<String, Object> p = new HashMap<>();
            p.put("vehicleNo", vehicleNo);
            
            return jdbcTemplate.queryForObject(sql, p, (rs, rowNum) -> {
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
