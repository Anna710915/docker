package com.example.onliner.controller;

import com.example.onliner.domain.exception.CustomError;
import com.example.onliner.domain.exception.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomError notFoundException(CustomNotFoundException e){
        return new CustomError(404, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomError badRequestError(Exception e) {
        return new CustomError(400, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomError internalServerError(Exception e) {
        return new CustomError(500, e.getMessage());
    }
}
