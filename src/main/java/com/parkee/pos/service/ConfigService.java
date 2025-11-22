package com.parkee.pos.service;

import com.parkee.pos.entity.Config;
import com.parkee.pos.repository.ConfigRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConfigService {

    private final ConfigRepository repo;

    public ConfigService(ConfigRepository repo) {
        this.repo = repo;
    }

    public List<Config> getConfigByStatusAndCode(String status, String code) {
        return repo.findByStatusAndCode(status, code);
    }
}
