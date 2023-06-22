package com.example.task1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SocksQuantityOutOfRangeException extends RuntimeException{
    public SocksQuantityOutOfRangeException(String message) {
        super(message);
    }
}
