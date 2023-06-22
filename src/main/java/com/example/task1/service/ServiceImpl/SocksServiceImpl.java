package com.example.task1.service.ServiceImpl;

import com.example.task1.dto.SocksDto;
import com.example.task1.exceptions.InvalidInputDataException;
import com.example.task1.mapper.SocksMapper;
import com.example.task1.model.SocksModel;
import com.example.task1.repository.SocksRepository;
import com.example.task1.service.SocksService;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.task1.constants.OperationsConstants.*;

@Service
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final SocksMapper socksMapper;

    public SocksServiceImpl(SocksRepository socksRepository, SocksMapper socksMapper) {
        this.socksRepository = socksRepository;
        this.socksMapper = socksMapper;
    }

    /**
     * It adds new quantity of socks to Data Base Table (socksDto.quantity) to previous quantity.<br>
     * If this type of socks doesn't exist it creates new record in DB Table<br>
     * It use {@link SocksMapper#socksDtoToSocksModel(SocksDto)}<br>
     * It use {@link SocksMapper#socksModelToSocksDto(SocksModel)}<br>
     * It use {@link SocksRepository#findByColorAndCottonPart}<br>
     * It use {@link SocksRepository#save(Object)}<br>
     * @param socksDto {@link SocksDto}
     * @return new socksDto {@link SocksDto}
     */
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

    /**
     * It reduces previous quantity of sockы in Data Base Table by an amount socksDto.quantity.<br>
     * If result of reducing is 0 socks it remove this type of socks from DB Table<br>
     * It use {@link SocksMapper#socksDtoToSocksModel(SocksDto)}<br>
     * It use {@link SocksMapper#socksModelToSocksDto(SocksModel)}<br>
     * It use {@link SocksRepository#findByColorAndCottonPart}<br>
     * It use {@link SocksRepository#save(Object)}<br>
     * @param socksDto {@link SocksDto}
     * @return new socksDto {@link SocksDto}
     */
    @Override
    public SocksDto outcomeSocks(SocksDto socksDto) {
        SocksModel socksModel = socksMapper.socksDtoToSocksModel(socksDto);
        SocksModel socksModelPrev = socksRepository.findByColorAndCottonPart(socksModel.getColor(), socksModel.getCottonPart()).orElse(null);

        if (socksModelPrev == null) {
            return null;
        }

        socksModelPrev.decQuantity(socksModel.getQuantity());
        if (socksModelPrev.getQuantity() == 0) {
            socksRepository.deleteById(socksModelPrev.getId());
        } else {
            socksRepository.save(socksModelPrev);
        }

        return socksMapper.socksModelToSocksDto(socksModelPrev);

    }

    /**
     *
     * @param color
     * @param operation - condition >, < or == (must be "MORETHAN", "LESSTHAN", "EQUAL" in any case of characters)
     * @param cottonPart (must be in integer range [0 - 100])
     * @return Optional<Integer> - total amount of socks for type:color AND cottonPart
     */
    @Override
    public Optional<Integer> getSocks(String color, String operation, int cottonPart) {
        if (!isValidData(color, operation, cottonPart)) {
            throw new InvalidInputDataException("Неверно переданы или отсутствую параметры GET запроса." +
                    "Пример корректного запроса: /api/socks?color=red&operation=moreThan&cottonPart=90");
        }

        Optional<Integer> resQuantitySocks;
        String colorValue = color.toUpperCase();
        switch (operation.toUpperCase()) {
            case MORETHAN:
                resQuantitySocks = socksRepository.findByColorWithCottonPartMoreThan(colorValue, cottonPart);
                break;
            case LESSTHAN:
                resQuantitySocks = socksRepository.findByColorWithCottonPartLessThan(colorValue, cottonPart);
                break;
            case EQUAL:
                resQuantitySocks = socksRepository.findByColorWithCottonPartEqual(colorValue, cottonPart);
                break;
            default:
                throw new InvalidInputDataException("Неверно передана команда по выборке из базы. Допустимые значения: " +
                        "MORETHAN, LESSTHAN, EQUAL. Регистр не имеет значения");
        }
        return resQuantitySocks;
    }

    /**
     * It checks validation of input data<br>
     * All parameters must be not empty
     * @param color
     * @param operation (must be "MORETHAN", "LESSTHAN", "EQUAL" in any case of characters)
     * @param cottonPart (must be in integer range [0 - 100])
     * @return true - input data is valid, otherwise - false
     */
    private boolean isValidData(String color, String operation, int cottonPart) {
        if (color.isEmpty() || operation.isEmpty() || cottonPart < 0 || cottonPart > 100) {
            return false;
        } else {
            return true;
        }
    }
}
