package com.conversor.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.conversor.common.StandarizedApiExceptionResponse;

@RestControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<StandarizedApiExceptionResponse> handleUnknownHostException(Exception ex){
		StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de conexion","error-1000",ex.getMessage());
		return new ResponseEntity<StandarizedApiExceptionResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(BussniesRulesException.class)
	public ResponseEntity<StandarizedApiExceptionResponse> handleBussniesRulesException(BussniesRulesException ex){
		StandarizedApiExceptionResponse response = new StandarizedApiExceptionResponse("Error de validacion",ex.getCode(),ex.getMessage());
		return new ResponseEntity<StandarizedApiExceptionResponse>(response,HttpStatus.PARTIAL_CONTENT);
	}

}
