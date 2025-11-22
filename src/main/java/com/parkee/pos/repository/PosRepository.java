package com.parkee.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.parkee.pos.entity.Pos;
import org.springframework.data.jpa.repository.Query;

public interface PosRepository extends JpaRepository<Pos, String> {

    @Query(value = "SELECT * FROM M_POS WHERE POS_CODE = :posCode AND STATUS = 'A'",  nativeQuery = true)
    Pos findByPosCode(String posCode);
}
