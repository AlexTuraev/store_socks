package com.example.task1.repository;

import com.example.task1.model.SocksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SocksRepository extends JpaRepository<SocksModel, Long> {
    Optional<SocksModel> findByColorAndCottonPart(String color, int cottonPart);

    @Query("SELECT SUM(sm.quantity) FROM SocksModel sm WHERE sm.color = :colorValue AND sm.cottonPart > :cottonPartValue")
    Optional<Integer> findByColorWithCottonPartMoreThan(String colorValue, int cottonPartValue);

    @Query("SELECT SUM(sm.quantity) FROM SocksModel sm WHERE sm.color = :colorValue AND sm.cottonPart < :cottonPartValue")
    Optional<Integer> findByColorWithCottonPartLessThan(String colorValue, int cottonPartValue);

    @Query("SELECT sm.quantity FROM SocksModel sm WHERE sm.color = :colorValue AND sm.cottonPart = :cottonPartValue")
    Optional<Integer> findByColorWithCottonPartEqual(String colorValue, int cottonPartValue);
}
