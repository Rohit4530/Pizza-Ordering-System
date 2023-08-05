package com.cart.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cart.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Long>{

}
