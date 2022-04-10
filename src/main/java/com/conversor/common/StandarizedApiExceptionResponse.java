package com.conversor.common;

import lombok.Data;

@Data
public class StandarizedApiExceptionResponse {
	
	private String type ="/errors/moneda";
	private String title;
	private String code;
	private String detail;
	private String instance="/errors/moneda";
	
	public StandarizedApiExceptionResponse(String title, String code, String detail) {
        super();
        this.title = title;
        this.code = code;
        this.detail = detail;
    }
	

}
