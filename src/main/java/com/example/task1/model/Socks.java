package com.example.task1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Socks {
    @Id
    @GeneratedValue
    private long id; // id
    private String color; // цвет носков
    private short cottonPart; // процент содержания хлопка
    private int quantity; // количество пар носков, целое число > 0
}
