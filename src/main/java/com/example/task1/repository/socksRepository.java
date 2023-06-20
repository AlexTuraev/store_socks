package com.example.task1.repository;

import com.example.task1.model.Socks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface socksRepository extends JpaRepository <Socks, Long> {

}
