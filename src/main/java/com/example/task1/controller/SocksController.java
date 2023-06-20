package com.example.task1.controller;

import com.example.task1.model.Socks;
import com.example.task1.service.SocksService;
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
    public String incomeSocks(@RequestBody Socks socks) {
        return "Hello";
    }
}
