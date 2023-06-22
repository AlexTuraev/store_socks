package com.example.task1.dto;

import lombok.Data;

@Data
public class SocksDto {
    private long id; // id
    // Ставим неверные данные для формирования исключения, в случае отсутствия данных
    private String color = ""; // цвет носков
    private int cottonPart = -1; // процент содержания хлопка
    private int quantity = -1; // количество пар носков, целое число > 0
}