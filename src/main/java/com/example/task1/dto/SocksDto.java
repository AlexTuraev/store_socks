package com.example.task1.dto;

import lombok.Data;

@Data
public class SocksDto {
    private long id; // id
    private String color; // цвет носков
    private int cottonPart; // процент содержания хлопка
    private int quantity; // количество пар носков, целое число > 0
}