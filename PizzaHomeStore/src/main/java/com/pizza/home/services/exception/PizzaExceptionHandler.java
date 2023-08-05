package com.pizza.home.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PizzaExceptionHandler {
	@ExceptionHandler(PizzaNotFoundException.class)
 public ResponseEntity<Object>handlePizzaNotFoundException(PizzaNotFoundException ex){
	 return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
 }
	@ExceptionHandler(PizzaNullException.class)
	 public ResponseEntity<Object>handlePizzaNotFoundException(PizzaNullException ex){
		 return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	 }
}
