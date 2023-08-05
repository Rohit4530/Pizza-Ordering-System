package com.sides.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class SidesExceptionHandler {
	@ExceptionHandler(SideNotFoundException.class)
	 public ResponseEntity<Object>handleSideNotFoundException(SideNotFoundException ex){
		 return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
	 }
	@ExceptionHandler(SideCannotUpdateException.class)
	 public ResponseEntity<Object>handleSideCannotUpdateException(SideCannotUpdateException ex){
		 return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	 }
	@ExceptionHandler(NullSideException.class)
	public ResponseEntity<Object>handleSideNullException(NullSideException ex ){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
		
}
