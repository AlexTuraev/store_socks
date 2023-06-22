package com.example.task1.service;

import com.example.task1.dto.SocksDto;
import com.example.task1.model.SocksModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface SocksService {
    SocksDto incomeSocks(SocksDto socks);

    SocksDto outcomeSocks(SocksDto socksDto);

    Optional<Integer> getSocks(String color, String operation, int cottonPart);
}
