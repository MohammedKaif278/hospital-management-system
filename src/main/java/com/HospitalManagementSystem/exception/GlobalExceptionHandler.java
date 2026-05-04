package com.HospitalManagementSystem.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.HospitalManagementSystem.payload.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse<Object>> handleNotFound(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
				new APIResponse<>("Failed", HttpStatus.NOT_FOUND.value(), ex.getMessage(), null, LocalDateTime.now()));
	}
	
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<APIResponse<Object>> handleGeneric(Exception ex)
//	{
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new APIResponse<>("Failes", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Something went wrong", null, LocalDateTime.now()));
//	}
}
