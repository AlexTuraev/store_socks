package com.example.task1.repository;

import com.example.task1.model.SocksModel;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocksRepository extends JpaRepository<SocksModel, Long> {
    Optional<SocksModel> findByColorAndCottonPart(String color, int cottonPart);
}
