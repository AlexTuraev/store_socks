package com.example.task1.service.ServiceImpl;

import com.example.task1.dto.SocksDto;
import com.example.task1.mapper.SocksMapper;
import com.example.task1.model.SocksModel;
import com.example.task1.repository.SocksRepository;
import com.example.task1.service.SocksService;
import org.springframework.stereotype.Service;

@Service
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final SocksMapper socksMapper;

    public SocksServiceImpl(SocksRepository socksRepository, SocksMapper socksMapper) {
        this.socksRepository = socksRepository;
        this.socksMapper = socksMapper;
    }

    public SocksDto incomeSocks(SocksDto socksDto) {
        SocksModel socksModel = socksMapper.socksDtoToSocksModel(socksDto);
        socksRepository.save(socksModel);
        return socksMapper.socksModelToSocksDto(socksModel);
    }
}
