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

    @Override
    public SocksDto incomeSocks(SocksDto socksDto) {
        SocksModel socksModel = socksMapper.socksDtoToSocksModel(socksDto);
        SocksModel socksModelPrev = socksRepository.findByColorAndCottonPart(socksModel.getColor(), socksModel.getCottonPart()).orElse(null);

        if (socksModelPrev != null) {
            socksModel.addQuantity(socksModelPrev.getQuantity());
            socksModel.setId(socksModelPrev.getId());
        }

        return socksMapper.socksModelToSocksDto(
                socksRepository.save(socksModel)
        );
    }

    @Override
    public SocksDto outcomeSocks(SocksDto socksDto) {
        SocksModel socksModel = socksMapper.socksDtoToSocksModel(socksDto);
        SocksModel socksModelPrev = socksRepository.findByColorAndCottonPart(socksModel.getColor(), socksModel.getCottonPart()).orElse(null);

        if (socksModelPrev != null) {
            socksModelPrev.decQuantity(socksModel.getQuantity());
            socksRepository.save(socksModelPrev);
            return socksMapper.socksModelToSocksDto(socksModelPrev);
        } else {
            return null;
        }
    }
}
