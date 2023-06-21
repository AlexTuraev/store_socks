package com.example.task1.controller;

import com.example.task1.dto.SocksDto;
import com.example.task1.service.SocksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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


    /*
        Регистрирует отпуск носков со склада
    */
    @PostMapping(value="/outcome", consumes="application/json")
    public ResponseEntity<?> outcomeSocks(@RequestBody SocksDto socksDto) {

        SocksDto socksDtoRes = socksService.outcomeSocks(socksDto);
        if (socksDtoRes == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(socksDtoRes);
        }
    }

    @GetMapping
    public ResponseEntity<?> getSocks(@RequestParam("color") String color,
                           @RequestParam("operation") String operation,
                           @RequestParam("cottonPart") int cottonPart
                           ) {

        Optional<Integer> socksQuantity = socksService.getSocks(color, operation, cottonPart);
        return ResponseEntity.ok(socksQuantity.orElse(0));
    }
}
