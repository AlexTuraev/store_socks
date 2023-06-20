package com.example.task1.service;

import com.example.task1.dto.SocksDto;
import com.example.task1.model.SocksModel;
import org.springframework.stereotype.Service;

public interface SocksService {
    public SocksDto incomeSocks(SocksDto socks);
}
