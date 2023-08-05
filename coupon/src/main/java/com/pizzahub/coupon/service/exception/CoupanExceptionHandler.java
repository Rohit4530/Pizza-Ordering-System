package com.pizzahub.coupon.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CoupanExceptionHandler {
    @ExceptionHandler(CouponNotFoundException.class)
	public ResponseEntity<Object>couponNotFoundExceptionHandler(CouponNotFoundException ex){
		return new ResponseEntity<Object>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
}
