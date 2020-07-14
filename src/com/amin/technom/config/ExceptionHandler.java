package com.amin.technom.config;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception ex) {
		
		return "error"; 
	}
}
