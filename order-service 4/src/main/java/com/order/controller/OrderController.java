package com.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.order.entity.Order;
import com.order.entity.OrderCart;
import com.order.repo.OrderRepo;
import com.order.service.OrderService;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
@RestController
@RequestMapping("/order")
public class OrderController{
	
	@Autowired
	private OrderService orderService; 
	
	@GetMapping("/all")
	public ResponseEntity<List<Order>> getOrders(){
		return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<Order>> findOrderById(@PathVariable Long userId){
		return new ResponseEntity<>(orderService.findOrderByUserId(userId), HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Order> addOrder(@RequestBody Order order){
		
			return new ResponseEntity<>(orderService.addOrder(order), HttpStatus.CREATED);
		
	}
	
	@PutMapping("/update/{id}/{status}")
	public ResponseEntity<?> updateOrderStatus(@PathVariable Long id, @PathVariable String status){
		   orderService.updateOrderStatus(id, status);
			return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteOrderById(@PathVariable Long id){
		    orderService.deleteOrder(id);
			return new ResponseEntity<>(HttpStatus.OK);
		
		
	}
	@GetMapping("carts/{orderId}")
	public ResponseEntity<List<OrderCart>>getOrderCartByOrderId(@PathVariable Long orderId){
		List<OrderCart> orders=orderService.getOrderCartByOrderId(orderId);
		return new ResponseEntity<>(orders,HttpStatus.OK);
	}
	@GetMapping("carts/user/{userId}")
	public ResponseEntity<List<OrderCart>>getCardByUserId(@PathVariable Long userId){
		List<OrderCart> carts=orderService.getCardsByUserId(userId);
		return new ResponseEntity<>(carts,HttpStatus.OK);
	}	

}
