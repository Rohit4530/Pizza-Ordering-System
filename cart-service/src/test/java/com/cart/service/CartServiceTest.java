package com.cart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.cart.entity.Cart;
import com.cart.service.exception.CannotAddNullCartException;
import com.cart.service.exception.CartNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class CartServiceTest {
    @Autowired
    private CartService cartService;

    
    @Test
    void testDeleteProductById() {
        assertThrows(CartNotFoundException.class, () -> cartService.deleteProductById(1012L));
        assertThrows(CartNotFoundException.class, () -> cartService.deleteProductById(2033L));
    }

    
    @Test
    void testAddProduct() {
        Cart cart = new Cart();
        cart.setAmount(10L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(1L);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("Product Name");
        cart.setSize("Size");
        cart.setUserId(1L);
        cartService.addProduct(cart);
        assertEquals(30L, cartService.getTotal());
    }

    
    @Test
    void testAddProduct2() {
        Cart cart = new Cart();
        cart.setAmount(0L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(1L);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("Product Name");
        cart.setSize("Size");
        cart.setUserId(1L);
        cartService.addProduct(cart);
        assertEquals(1, cartService.getAllProducts().size());
    }

    
    @Test
    void testAddProduct3() {
        Cart cart = new Cart();
        cart.setAmount(10L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(null);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("Product Name");
        cart.setSize("Size");
        cart.setUserId(1L);
        assertThrows(CannotAddNullCartException.class, () -> cartService.addProduct(cart));
    }

    
    @Test
    void testUpdateProduct() {
        Cart cart = new Cart();
        cart.setAmount(10L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(1L);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("Product Name");
        cart.setSize("Size");
        cart.setUserId(1L);
        cartService.updateProduct(cart);
        assertEquals(30L, cartService.getTotal());
    }

   
    @Test
    void testUpdateProduct2() {
        Cart cart = new Cart();
        cart.setAmount(0L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(1L);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("Product Name");
        cart.setSize("Size");
        cart.setUserId(1L);
        cartService.updateProduct(cart);
        assertEquals(1, cartService.getAllProducts().size());
    }

    
    @Test
    void testUpdateProduct3() {
        Cart cart = new Cart();
        cart.setAmount(10L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(null);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("Product Name");
        cart.setSize("Size");
        cart.setUserId(1L);
        assertThrows(CannotAddNullCartException.class, () -> cartService.updateProduct(cart));
    }
}

