package com.cart.controller;

import java.util.List;
import java.util.Optional;

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

import com.cart.entity.Cart;
import com.cart.service.CartService;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService cartService;
	@GetMapping("/products")
	public ResponseEntity<List<Cart>> getProduct(){
		return new ResponseEntity<>(cartService.getAllProducts(),HttpStatus.OK);
	}
	@GetMapping("/product/{id}")
	public ResponseEntity<Optional<Cart>> getProductById(@PathVariable Long id){
		return new ResponseEntity<>(cartService.getProductById(id),HttpStatus.OK);
	}
	@GetMapping("/products/{userId}")
	public ResponseEntity<List<Cart>>getUserByUserId(@PathVariable Long userId){
	List<Cart>userCart=	cartService.getProductByUserId(userId);
		return new ResponseEntity<>(userCart,HttpStatus.OK);
	}
	
	@DeleteMapping("/product/delete/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable Long id){
		cartService.deleteProductById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	 @PostMapping("product/add")
	   public ResponseEntity<Cart>addPizza(@RequestBody Cart cart){  
				cartService.addProduct(cart);
				return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
	   }
	@PutMapping("/product/update")
	public ResponseEntity<Cart> updateProduct(@RequestBody Cart cart){
		return new ResponseEntity<>(cartService.updateProduct(cart),HttpStatus.OK);
	}
	@GetMapping("/products/total")
	public ResponseEntity<Long>getFinalAmounty(){
		 Long ans=cartService.getTotal();
		return new ResponseEntity<>(ans,HttpStatus.OK);
	}

}
