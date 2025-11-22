package com.parkee.pos.repository;

import com.parkee.pos.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConfigRepository extends JpaRepository<Config, String> {

    List<Config> findByStatusAndCode(String status, String code);

}
