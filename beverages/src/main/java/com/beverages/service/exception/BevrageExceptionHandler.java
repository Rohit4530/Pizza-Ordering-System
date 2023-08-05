package com.beverages.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BevrageExceptionHandler {
	@ExceptionHandler(BeverageCreateException.class)
	public ResponseEntity<Object>BevrageCreateExceptionHandler(BeverageCreateException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BevrageUpdateException.class)
	public ResponseEntity<Object>BevrageUpdateExceptionHandler(BevrageUpdateException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(BeverageNotFoundException.class)
	public ResponseEntity<Object>BevrageUpdateExceptionHandler(BeverageNotFoundException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
