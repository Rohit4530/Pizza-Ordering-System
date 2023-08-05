package com.order.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.order.entity.Order;
import com.order.entity.OrderCart;
import com.order.repo.OrderCartRepo;
import com.order.repo.OrderRepo;
import com.order.service.OrderService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {OrderController.class, OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @MockBean
    private OrderCartRepo orderCartRepo;

    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderRepo orderRepo;

    
    @Test
    @Disabled("TODO: Complete this test")
    void testAddOrder() {
        

        OrderController orderController = new OrderController();

        Order order = new Order();
        order.setAddress("42 Main St");
        order.setAmount(10.0d);
        order.setCarts(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        order.setId(1L);
        order.setStatus("Status");
        order.setUserId(1L);
        orderController.addOrder(order);
    }

    
    @Test
    @Disabled("TODO: Complete this test")
    void testAddOrder2() {
        
        OrderController orderController = new OrderController();
        Order order = mock(Order.class);
        doNothing().when(order).setAddress((String) any());
        doNothing().when(order).setAmount(anyDouble());
        doNothing().when(order).setCarts((List<OrderCart>) any());
        doNothing().when(order).setDate((Date) any());
        doNothing().when(order).setId((Long) any());
        doNothing().when(order).setStatus((String) any());
        doNothing().when(order).setUserId(anyLong());
        order.setAddress("42 Main St");
        order.setAmount(10.0d);
        order.setCarts(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        order.setId(1L);
        order.setStatus("Status");
        order.setUserId(1L);
        orderController.addOrder(order);
    }

    
    @Test
    void testDeleteOrderById() throws Exception {
        doNothing().when(orderRepo).deleteById((Long) any());
        when(orderRepo.existsById((Long) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/order/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
    @Test
    void testFindOrderById() throws Exception {
        when(orderRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testFindOrderById2() throws Exception {
        Order order = new Order();
        order.setAddress("42 Main St");
        order.setAmount(10.0d);
        order.setCarts(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        order.setId(1L);
        order.setStatus("?");
        order.setUserId(1L);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(orderRepo.findAll()).thenReturn(orderList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"carts\":[],\"userId\":1,\"date\":0,\"status\":\"?\",\"amount\":10.0,\"address\":\"42 Main St\",\"id\":1}]"));
    }

    
    @Test
    void testFindOrderById3() throws Exception {
        Order order = new Order();
        order.setAddress("42 Main St");
        order.setAmount(10.0d);
        order.setCarts(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        order.setId(1L);
        order.setStatus("?");
        order.setUserId(1L);

        Order order1 = new Order();
        order1.setAddress("17 High St");
        order1.setAmount(0.5d);
        order1.setCarts(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        order1.setId(2L);
        order1.setStatus("U");
        order1.setUserId(2L);

        ArrayList<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order);
        when(orderRepo.findAll()).thenReturn(orderList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"carts\":[],\"userId\":1,\"date\":0,\"status\":\"?\",\"amount\":10.0,\"address\":\"42 Main St\",\"id\":1}]"));
    }

    
    @Test
    void testGetCardByUserId() throws Exception {
        when(orderCartRepo.findByUserId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/carts/user/{userId}", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetCardByUserId2() throws Exception {
        when(orderCartRepo.findByUserId((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/order/carts/user/{userId}", 1L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetOrderCartByOrderId() throws Exception {
        when(orderCartRepo.findByOrders((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/carts/{orderId}", 1L);
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetOrderCartByOrderId2() throws Exception {
        when(orderCartRepo.findByOrders((Long) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/order/carts/{orderId}", 1L);
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetOrders() throws Exception {
        when(orderRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order/all");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetOrders2() throws Exception {
        when(orderRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/order/all");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testUpdateOrderStatus() throws Exception {
        Order order = new Order();
        order.setAddress("42 Main St");
        order.setAmount(10.0d);
        order.setCarts(new ArrayList<>());
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        order.setDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        order.setId(1L);
        order.setStatus("Status");
        order.setUserId(1L);
        Optional<Order> ofResult = Optional.of(order);

        Order order1 = new Order();
        order1.setAddress("42 Main St");
        order1.setAmount(10.0d);
        order1.setCarts(new ArrayList<>());
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        order1.setDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        order1.setId(1L);
        order1.setStatus("Status");
        order1.setUserId(1L);
        when(orderRepo.save((Order) any())).thenReturn(order1);
        when(orderRepo.findById((Long) any())).thenReturn(ofResult);
        when(orderRepo.existsById((Long) any())).thenReturn(true);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/order/update/{id}/{status}", 1L,
                "Status");
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

