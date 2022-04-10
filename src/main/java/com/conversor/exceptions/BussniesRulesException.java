package com.conversor.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class BussniesRulesException extends Exception{
	

	private long id;
	private String code;
	private HttpStatus httpStatus;
	
	public BussniesRulesException(long id, String code,String message, HttpStatus httpStatus) {
		super(message);
		this.id = id;
		this.code = code;
		this.httpStatus = httpStatus;
	}

	public BussniesRulesException(String code, String message, HttpStatus httpStatus) {
		super(message);
		this.code = code;
		this.httpStatus = httpStatus;
	}
	

	
	
}
