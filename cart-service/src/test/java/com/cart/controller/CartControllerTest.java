package com.cart.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.cart.entity.Cart;
import com.cart.repo.CartRepo;
import com.cart.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CartController.class, CartService.class})
@ExtendWith(SpringExtension.class)
class CartControllerTest {

    @Autowired
    private CartController cartController;

    @MockBean
    private CartRepo cartRepo;

    
    @Test
    void testAddPizza() throws Exception {
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
        when(cartRepo.save((Cart) any())).thenReturn(cart);

        Cart cart1 = new Cart();
        cart1.setAmount(10L);
        cart1.setCrustId(1L);
        cart1.setDescription("The characteristics of someone or something");
        cart1.setId(1L);
        cart1.setImageUrl("https://example.org/example");
        cart1.setOrderCount(3L);
        cart1.setProductId(1L);
        cart1.setProductName("Product Name");
        cart1.setSize("Size");
        cart1.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(cart1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/cart/product/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"productId\":1,\"productName\":\"Product Name\",\"userId\":1,\"crustId\":1,\"orderCount\":3,\"amount\":10"
                                        + ",\"size\":\"Size\",\"imageUrl\":\"https://example.org/example\",\"description\":\"The characteristics of someone"
                                        + " or something\"}"));
    }

   
    @Test
    void testDeleteProductById() throws Exception {
        doNothing().when(cartRepo).deleteById((Long) any());
        when(cartRepo.existsById((Long) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/cart/product/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
    @Test
    void testGetFinalAmounty() throws Exception {
        when(cartRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/products/total");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("0"));
    }

    
    @Test
    void testGetFinalAmounty2() throws Exception {
        Cart cart = new Cart();
        cart.setAmount(10L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(1L);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("?");
        cart.setSize("?");
        cart.setUserId(1L);

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(cart);
        when(cartRepo.findAll()).thenReturn(cartList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/products/total");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("30"));
    }

   
    @Test
    void testGetProduct() throws Exception {
        when(cartRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/products");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetProduct2() throws Exception {
        when(cartRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/cart/products");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetProductById() throws Exception {
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
        Optional<Cart> ofResult = Optional.of(cart);
        when(cartRepo.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/product/{id}", 1L);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"productId\":1,\"productName\":\"Product Name\",\"userId\":1,\"crustId\":1,\"orderCount\":3,\"amount\":10"
                                        + ",\"size\":\"Size\",\"imageUrl\":\"https://example.org/example\",\"description\":\"The characteristics of someone"
                                        + " or something\"}"));
    }

    
    @Test
    void testGetUserByUserId() throws Exception {
        when(cartRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/products/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetUserByUserId2() throws Exception {
        Cart cart = new Cart();
        cart.setAmount(10L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(1L);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("?");
        cart.setSize("?");
        cart.setUserId(1L);

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(cart);
        when(cartRepo.findAll()).thenReturn(cartList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/products/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"productId\":1,\"productName\":\"?\",\"userId\":1,\"crustId\":1,\"orderCount\":3,\"amount\":10,\"size\""
                                        + ":\"?\",\"imageUrl\":\"https://example.org/example\",\"description\":\"The characteristics of someone or"
                                        + " something\"}]"));
    }

    
    @Test
    void testGetUserByUserId3() throws Exception {
        Cart cart = new Cart();
        cart.setAmount(10L);
        cart.setCrustId(1L);
        cart.setDescription("The characteristics of someone or something");
        cart.setId(1L);
        cart.setImageUrl("https://example.org/example");
        cart.setOrderCount(3L);
        cart.setProductId(1L);
        cart.setProductName("?");
        cart.setSize("?");
        cart.setUserId(1L);

        Cart cart1 = new Cart();
        cart1.setAmount(1L);
        cart1.setCrustId(2L);
        cart1.setDescription("?");
        cart1.setId(2L);
        cart1.setImageUrl("?");
        cart1.setOrderCount(1L);
        cart1.setProductId(2L);
        cart1.setProductName("U");
        cart1.setSize("U");
        cart1.setUserId(2L);

        ArrayList<Cart> cartList = new ArrayList<>();
        cartList.add(cart1);
        cartList.add(cart);
        when(cartRepo.findAll()).thenReturn(cartList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/products/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"productId\":1,\"productName\":\"?\",\"userId\":1,\"crustId\":1,\"orderCount\":3,\"amount\":10,\"size\""
                                        + ":\"?\",\"imageUrl\":\"https://example.org/example\",\"description\":\"The characteristics of someone or"
                                        + " something\"}]"));
    }

    
    @Test
    void testGetUserByUserId4() throws Exception {
        when(cartRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cart/products/{userId}", "",
                "Uri Variables");
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testUpdateProduct() throws Exception {
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
        when(cartRepo.save((Cart) any())).thenReturn(cart);

        Cart cart1 = new Cart();
        cart1.setAmount(10L);
        cart1.setCrustId(1L);
        cart1.setDescription("The characteristics of someone or something");
        cart1.setId(1L);
        cart1.setImageUrl("https://example.org/example");
        cart1.setOrderCount(3L);
        cart1.setProductId(1L);
        cart1.setProductName("Product Name");
        cart1.setSize("Size");
        cart1.setUserId(1L);
        String content = (new ObjectMapper()).writeValueAsString(cart1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/cart/product/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"productId\":1,\"productName\":\"Product Name\",\"userId\":1,\"crustId\":1,\"orderCount\":3,\"amount\":10"
                                        + ",\"size\":\"Size\",\"imageUrl\":\"https://example.org/example\",\"description\":\"The characteristics of someone"
                                        + " or something\"}"));
    }

}

