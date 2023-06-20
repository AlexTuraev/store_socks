package com.example.task1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDtoDataException extends IllegalArgumentException{
    public InvalidDtoDataException(String s) {
        super(s);
    }
}
