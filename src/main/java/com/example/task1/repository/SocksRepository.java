package com.example.task1.repository;

import com.example.task1.model.SocksModel;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;

//@EntityScan({"com.example.task1.model.*"})
public interface SocksRepository extends JpaRepository<SocksModel, Long> {
}
