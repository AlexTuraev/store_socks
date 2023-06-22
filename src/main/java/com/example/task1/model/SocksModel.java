package com.example.task1.model;

import com.example.task1.exceptions.SocksQuantityOutOfRangeException;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

//import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "socks_model")
@Data
public class SocksModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // id

    private String color; // цвет носков
    @Column(name="cotton_part")
    private int cottonPart; // процент содержания хлопка
    private int quantity; // количество пар носков, целое число > 0

    public void addQuantity(int q) {
        this.quantity += q;
    }

    public void decQuantity(int q) {
        if (this.quantity - q < 0) {
            throw new SocksQuantityOutOfRangeException("Нет такого количества носков для списания");
        }
        this.quantity -= q;
    }
}