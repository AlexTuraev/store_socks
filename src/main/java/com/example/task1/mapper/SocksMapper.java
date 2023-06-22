package com.example.task1.mapper;

import com.example.task1.dto.SocksDto;
import com.example.task1.exceptions.InvalidInputDataException;
import com.example.task1.model.SocksModel;
import org.springframework.stereotype.Component;

@Component
public class SocksMapper {
    /**
     * Transform SocksDto to SocksModel
     * @param socksDto {@link SocksDto}
     * @return {@link SocksModel}
     */
    public SocksModel socksDtoToSocksModel(SocksDto socksDto) {
        if (!isValidData(socksDto)) {
            throw new InvalidInputDataException("Внимание! cottonPart >=0 && cottonPart <=100 && quantity > 0");
        }
        SocksModel socksModel = new SocksModel();
        socksModel.setColor(socksDto.getColor().toUpperCase());
        socksModel.setCottonPart(socksDto.getCottonPart());
        socksModel.setQuantity(socksDto.getQuantity());
        return socksModel;
    }

    /**
     * Transform SocksModel to SocksDto
     * @param socksModel {@link SocksModel}
     * @return socksDto {@link SocksDto}
     */
    public SocksDto socksModelToSocksDto(SocksModel socksModel) {
        SocksDto socksDto = new SocksDto();
        socksDto.setColor(socksModel.getColor());
        socksDto.setCottonPart(socksModel.getCottonPart());
        socksDto.setQuantity(socksModel.getQuantity());
        socksDto.setId(socksModel.getId());
        return socksDto;
    }

    /**
     * Validation data
     * @param socksDto {@link SocksDto}
     * @return true when input data is valid, otherwise - false
     */
    private boolean isValidData(SocksDto socksDto) {
        if (socksDto.getQuantity() <= 0 || socksDto.getCottonPart() < 0 || socksDto.getCottonPart() > 100
        || socksDto.getColor().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
