package com.beverages.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.beverages.entity.Beverages;
import com.beverages.repo.BeveragesRepo;
import com.beverages.service.BeveragesService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {BeveragesController.class, BeveragesService.class})
@ExtendWith(SpringExtension.class)
class BeveragesControllerTest {
    @Autowired
    private BeveragesController beveragesController;

    @MockBean
    private BeveragesRepo beveragesRepo;

    
    @Test
    void testAddBeverages() throws Exception {
        Beverages beverages = new Beverages();
        beverages.setBeverageCount(3L);
        beverages.setBeverageDisc("Beverage Disc");
        beverages.setBeverageName("Beverage Name");
        beverages.setBeveragePrice(1L);
        beverages.setBeverageQuant("Beverage Type");
        beverages.setId(1L);
        beverages.setImgURL("https://example.org/example");
        when(beveragesRepo.save((Beverages) any())).thenReturn(beverages);

        Beverages beverages1 = new Beverages();
        beverages1.setBeverageCount(3L);
        beverages1.setBeverageDisc("Beverage Disc");
        beverages1.setBeverageName("Beverage Name");
        beverages1.setBeveragePrice(1L);
        beverages1.setBeverageQuant("Beverage Type");
        beverages1.setId(1L);
        beverages1.setImgURL("https://example.org/example");
        String content = (new ObjectMapper()).writeValueAsString(beverages1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/beverages/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"beverageName\":\"Beverage Name\",\"beverageQuant\":\"Beverage Type\",\"beverageDisc\":\"Beverage"
                                        + " Disc\",\"beveragePrice\":1,\"beverageCount\":3,\"imgURL\":\"https://example.org/example\"}"));
    }

    
    @Test
    void testDeleteBeverages() throws Exception {
        Beverages beverages = new Beverages();
        beverages.setBeverageCount(3L);
        beverages.setBeverageDisc("Beverage Disc");
        beverages.setBeverageName("Beverage Name");
        beverages.setBeveragePrice(1L);
        beverages.setBeverageQuant("Beverage Type");
        beverages.setId(1L);
        beverages.setImgURL("https://example.org/example");
        Optional<Beverages> ofResult = Optional.of(beverages);
        doNothing().when(beveragesRepo).deleteById((Long) any());
        when(beveragesRepo.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/beverages/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
    @Test
    void testGetBeveragesByName() throws Exception {
        Beverages beverages = new Beverages();
        beverages.setBeverageCount(3L);
        beverages.setBeverageDisc("Beverage Disc");
        beverages.setBeverageName("Beverage Name");
        beverages.setBeveragePrice(1L);
        beverages.setBeverageQuant("Beverage Type");
        beverages.setId(1L);
        beverages.setImgURL("https://example.org/example");
        Optional<Beverages> ofResult = Optional.of(beverages);
        when(beveragesRepo.findByBeverageName((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beverages/{name}", "Name");
        MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"beverageName\":\"Beverage Name\",\"beverageQuant\":\"Beverage Type\",\"beverageDisc\":\"Beverage"
                                        + " Disc\",\"beveragePrice\":1,\"beverageCount\":3,\"imgURL\":\"https://example.org/example\"}"));
    }

    
    @Test
    void testGetallBeveragesOrderByPopularity() throws Exception {
        when(beveragesRepo.findAll((Sort) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beverages/all/orderByPopularity");
        MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallBeveragesOrderByPopularity2() throws Exception {
        when(beveragesRepo.findAll((Sort) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/beverages/all/orderByPopularity");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallSides() throws Exception {
        when(beveragesRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beverages/all");
        MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallSides2() throws Exception {
        when(beveragesRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/beverages/all");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallSidesOrderbyPrice() throws Exception {
        when(beveragesRepo.findAll((Sort) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/beverages/all/orderbyPrice");
        MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallSidesOrderbyPrice2() throws Exception {
        when(beveragesRepo.findAll((Sort) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/beverages/all/orderbyPrice");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testUpdateSides() throws Exception {
        Beverages beverages = new Beverages();
        beverages.setBeverageCount(3L);
        beverages.setBeverageDisc("Beverage Disc");
        beverages.setBeverageName("Beverage Name");
        beverages.setBeveragePrice(1L);
        beverages.setBeverageQuant("Beverage Type");
        beverages.setId(1L);
        beverages.setImgURL("https://example.org/example");
        when(beveragesRepo.save((Beverages) any())).thenReturn(beverages);

        Beverages beverages1 = new Beverages();
        beverages1.setBeverageCount(3L);
        beverages1.setBeverageDisc("Beverage Disc");
        beverages1.setBeverageName("Beverage Name");
        beverages1.setBeveragePrice(1L);
        beverages1.setBeverageQuant("Beverage Type");
        beverages1.setId(1L);
        beverages1.setImgURL("https://example.org/example");
        String content = (new ObjectMapper()).writeValueAsString(beverages1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/beverages/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(beveragesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"beverageName\":\"Beverage Name\",\"beverageQuant\":\"Beverage Type\",\"beverageDisc\":\"Beverage"
                                        + " Disc\",\"beveragePrice\":1,\"beverageCount\":3,\"imgURL\":\"https://example.org/example\"}"));
    }
}

