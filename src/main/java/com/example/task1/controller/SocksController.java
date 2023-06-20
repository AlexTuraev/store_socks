package com.example.task1.controller;

import com.example.task1.dto.SocksDto;
import com.example.task1.model.SocksModel;
import com.example.task1.service.SocksService;
import jakarta.persistence.GeneratedValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
public class SocksController {
    private final SocksService socksService;

    public SocksController(SocksService socksService) {
        this.socksService = socksService;
    }


    /*
        Регистрирует приход носков на склад.
    */
    @PostMapping(value="/income", consumes="application/json")
    public ResponseEntity<?> incomeSocks(@RequestBody SocksDto socksDto) {
        return ResponseEntity.ok(socksService.incomeSocks(socksDto));
    }

   /* @GetMapping("/get")
    public String getSocks() {
        return "Hello from Socks Store";
    }*/
}
