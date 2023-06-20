package com.example.task1.mapper;

import com.example.task1.dto.SocksDto;
import com.example.task1.model.SocksModel;
import org.springframework.stereotype.Component;

@Component
public class SocksMapper {
    public SocksModel socksDtoToSocksModel(SocksDto socksDto) {
        SocksModel socksModel = new SocksModel();
        socksModel.setColor(socksDto.getColor());
        socksModel.setCottonPart(socksDto.getCottonPart());
        socksModel.setQuantity(socksDto.getQuantity());
        return socksModel;
    }

    public SocksDto socksModelToSocksDto(SocksModel socksModel) {
        SocksDto socksDto = new SocksDto();
        socksDto.setColor(socksModel.getColor());
        socksDto.setCottonPart(socksModel.getCottonPart());
        socksDto.setQuantity(socksModel.getQuantity());
        return socksDto;
    }
}
