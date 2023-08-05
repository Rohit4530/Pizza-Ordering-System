package com.order.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderExceptionHandler {
	
	@ExceptionHandler(OrderUpdateFailedException.class)
	public ResponseEntity<Object>orderUpdateFailureException(OrderUpdateFailedException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoOrderWithUserIdException.class)
	public ResponseEntity<Object>NoOrderWithUserIdException(NoOrderWithUserIdException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadOrderException.class)
	public ResponseEntity<Object>IncompatibleOrderException(BadOrderException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DeleteFaliureException.class)
	public ResponseEntity<Object>DeleteFaliureException(DeleteFaliureException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
