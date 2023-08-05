package com.pizza.home.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizza.home.model.Pizza;
import com.pizza.home.services.PizzaServices;

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

@ContextConfiguration(classes = {PizzaController.class})
@ExtendWith(SpringExtension.class)
class PizzaControllerTest {
    @Autowired
    private PizzaController pizzaController;

    @MockBean
    private PizzaServices pizzaServices;

   
    @Test
    void testAddPizza() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        when(pizzaServices.createPizza((Pizza) any())).thenReturn(pizza);

        Pizza pizza1 = new Pizza();
        pizza1.setId(1);
        pizza1.setImgURL("https://example.org/example");
        pizza1.setPizzaCount(3L);
        pizza1.setPizzaDesc("Pizza Desc");
        pizza1.setPizzaName("Pizza Name");
        pizza1.setPizzaPrice(10.0d);
        pizza1.setPizzaType("Pizza Type");
        String content = (new ObjectMapper()).writeValueAsString(pizza1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/pizza/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"pizzaName\":\"Pizza Name\",\"pizzaType\":\"Pizza Type\",\"pizzaPrice\":10.0,\"pizzaDesc\":\"Pizza"
                                        + " Desc\",\"pizzaCount\":3,\"imgURL\":\"https://example.org/example\"}"));
    }

    
    @Test
    void testDeletePizza() throws Exception {
        doNothing().when(pizzaServices).deleteById((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/pizza/{id}", 1);
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
    @Test
    void testUpdatePizza() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        when(pizzaServices.updatePizza((Pizza) any())).thenReturn(pizza);

        Pizza pizza1 = new Pizza();
        pizza1.setId(1);
        pizza1.setImgURL("https://example.org/example");
        pizza1.setPizzaCount(3L);
        pizza1.setPizzaDesc("Pizza Desc");
        pizza1.setPizzaName("Pizza Name");
        pizza1.setPizzaPrice(10.0d);
        pizza1.setPizzaType("Pizza Type");
        String content = (new ObjectMapper()).writeValueAsString(pizza1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/pizza/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    
    @Test
    void testDeletePizza2() throws Exception {
        doNothing().when(pizzaServices).deleteById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/pizza/{id}", 1);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
    @Test
    void testGetPizzaByName() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        Optional<Pizza> ofResult = Optional.of(pizza);
        when(pizzaServices.findByPizzaName((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizza/{name}", "Name");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"pizzaName\":\"Pizza Name\",\"pizzaType\":\"Pizza Type\",\"pizzaPrice\":10.0,\"pizzaDesc\":\"Pizza"
                                        + " Desc\",\"pizzaCount\":3,\"imgURL\":\"https://example.org/example\"}"));
    }

   
    @Test
    void testGetallPizza() throws Exception {
        when(pizzaServices.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizza/all");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

   
    @Test
    void testGetallPizza2() throws Exception {
        when(pizzaServices.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/pizza/all");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetallPizzaOrderByPopularity() throws Exception {
        when(pizzaServices.getAllSortedByOrderCount()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizza/all/orderByPopularity");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

   
    @Test
    void testGetallPizzaOrderByPopularity2() throws Exception {
        when(pizzaServices.getAllSortedByOrderCount()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/pizza/all/orderByPopularity");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallPizzaOrderByPrice() throws Exception {
        when(pizzaServices.getAllSortedByPrice()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/pizza/all/orderByPrice");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

   
    @Test
    void testGetallPizzaOrderByPrice2() throws Exception {
        when(pizzaServices.getAllSortedByPrice()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/pizza/all/orderByPrice");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(pizzaController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

