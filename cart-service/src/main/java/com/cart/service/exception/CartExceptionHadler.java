package com.cart.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CartExceptionHadler {
	@ExceptionHandler(CannotAddNullCartException.class)
	public ResponseEntity<Object>cannotAddNullCartException(CannotAddNullCartException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Object>CartNotFoundExceptionHandler(CartNotFoundException ex){
    	return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
