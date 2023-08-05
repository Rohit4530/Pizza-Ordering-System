package com.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.entity.Cart;
import com.cart.repo.CartRepo;
import com.cart.service.exception.CannotAddNullCartException;
import com.cart.service.exception.CartNotFoundException;

@Service
public class CartService {
	@Autowired
	private CartRepo cartRepo;

	public List<Cart> getAllProducts() {
		return cartRepo.findAll();
	}

	public Optional<Cart> getProductById(Long id) {
		Optional<Cart> cart=cartRepo.findById(id);
		if(cart.isPresent()) {
			return cart;
		}else {
			throw new CartNotFoundException("Cart with ID " + id + " not found");
		}
	}

	public void deleteProductById(Long id) {
			if(cartRepo.existsById(id)) {
				cartRepo.deleteById(id);
				
			}else {
				throw new CartNotFoundException("Cart with ID "+id+" not found");
			}

	}

	public Cart addProduct(Cart cart) {
		if(cart.getProductName()!=null)
		 return cartRepo.save(cart);
		else
			throw new CannotAddNullCartException("The Cart you are adding might be null");
	}

	public Cart updateProduct(Cart cart) {
		if(cart.getId()!=null)
			return cartRepo.save(cart);
			else
				throw new CannotAddNullCartException("Please provide cartId to update cart");
	}
  public List<Cart>	getProductByUserId(Long userId){
		return cartRepo.findAll().stream().filter(e->e.getUserId()==userId).toList();
//	List<Cart>carts=cartRepo.findAll();
//	List<Cart>userCart=new ArrayList<>();
//	for(Cart cart:carts) {
//		if(cart.getUserId()==userId) {
//			userCart.add(cart);
//		  }
//	   }
//	   return userCart;
	}
  public long getTotal() {
	 List<Cart> carts=cartRepo.findAll();
	// long finalAmount= carts.stream().mapToLong(Cart::getAmount).sum();

//	 for(Cart cart:carts) {
//		 finalAmount=finalAmount+cart.getAmount()*cart.getOrderCount();
//	 }
	 return cartRepo.findAll().stream().mapToLong(Cart::getAmount).sum();
  }
}
