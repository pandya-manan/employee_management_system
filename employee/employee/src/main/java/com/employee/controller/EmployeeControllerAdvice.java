package com.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.exception.EmployeeManagementSystemException;

import java.util.*;

@RestControllerAdvice
public class EmployeeControllerAdvice {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
	
	@ExceptionHandler(EmployeeManagementSystemException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeExceptions(EmployeeManagementSystemException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Employee management system error");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
