package com.saiDeveloper.Spring_Boot_Starter_Template.exception;

import com.saiDeveloper.Spring_Boot_Starter_Template.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserException.class)
    public ResponseEntity<ExceptionResponse> userExceptionHandler(UserException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseException.class)
    public ResponseEntity<ExceptionResponse> userExceptionHandler(CourseException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);


    }
    }
