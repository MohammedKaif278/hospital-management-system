package com.HospitalManagementSystem.payload;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

	private String status;
	private int statusCode;
	private String message;
	private T data;
	private LocalDateTime timestamp;
	
}
