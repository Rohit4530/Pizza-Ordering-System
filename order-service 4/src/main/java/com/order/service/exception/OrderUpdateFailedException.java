package com.order.service.exception;

public class OrderUpdateFailedException extends RuntimeException {

	public OrderUpdateFailedException(String message) {
		super(message);
	}

}
