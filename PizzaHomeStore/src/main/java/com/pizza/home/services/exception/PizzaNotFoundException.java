package com.pizza.home.services.exception;

public class PizzaNotFoundException extends RuntimeException {
	public PizzaNotFoundException(String msg){
		super(msg);
	}
     
}
