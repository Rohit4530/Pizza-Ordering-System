package com.sides.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sides.entity.Sides;
import com.sides.repo.SidesRepo;
import com.sides.service.SidesService;

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

@ContextConfiguration(classes = {SidesController.class, SidesService.class})
@ExtendWith(SpringExtension.class)
class SidesControllerTest {
    @Autowired
    private SidesController sidesController;

    @MockBean
    private SidesRepo sidesRepo;

    
    @Test
    void testAddSides() throws Exception {
        Sides sides = new Sides();
        sides.setId(1L);
        sides.setImgURL("https://example.org/example");
        sides.setSideCount(1L);
        sides.setSideDesc("Side Desc");
        sides.setSideName("Side Name");
        sides.setSidePrice(1L);
        sides.setSideType("Side Type");
        when(sidesRepo.save((Sides) any())).thenReturn(sides);

        Sides sides1 = new Sides();
        sides1.setId(1L);
        sides1.setImgURL("https://example.org/example");
        sides1.setSideCount(1L);
        sides1.setSideDesc("Side Desc");
        sides1.setSideName("Side Name");
        sides1.setSidePrice(1L);
        sides1.setSideType("Side Type");
        String content = (new ObjectMapper()).writeValueAsString(sides1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/sides/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"sideName\":\"Side Name\",\"sideType\":\"Side Type\",\"sidePrice\":1,\"sideCount\":1,\"sideDesc\":\"Side"
                                        + " Desc\",\"imgURL\":\"https://example.org/example\"}"));
    }

    
    @Test
    void testDeleteSides() throws Exception {
        Sides sides = new Sides();
        sides.setId(1L);
        sides.setImgURL("https://example.org/example");
        sides.setSideCount(1L);
        sides.setSideDesc("Side Desc");
        sides.setSideName("Side Name");
        sides.setSidePrice(1L);
        sides.setSideType("Side Type");
        Optional<Sides> ofResult = Optional.of(sides);
        doNothing().when(sidesRepo).deleteById((Long) any());
        when(sidesRepo.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/sides/delete/{id}", 1L);
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    
    @Test
    void testGetSidesById() throws Exception {
        Sides sides = new Sides();
        sides.setId(1L);
        sides.setImgURL("https://example.org/example");
        sides.setSideCount(1L);
        sides.setSideDesc("Side Desc");
        sides.setSideName("Side Name");
        sides.setSidePrice(1L);
        sides.setSideType("Side Type");
        Optional<Sides> ofResult = Optional.of(sides);
        when(sidesRepo.findById((Long) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sides/find/{id}", 1L);
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"sideName\":\"Side Name\",\"sideType\":\"Side Type\",\"sidePrice\":1,\"sideCount\":1,\"sideDesc\":\"Side"
                                        + " Desc\",\"imgURL\":\"https://example.org/example\"}"));
    }

    
    @Test
    void testGetSidesByName() throws Exception {
        Sides sides = new Sides();
        sides.setId(1L);
        sides.setImgURL("https://example.org/example");
        sides.setSideCount(1L);
        sides.setSideDesc("Side Desc");
        sides.setSideName("Side Name");
        sides.setSidePrice(1L);
        sides.setSideType("Side Type");
        Optional<Sides> ofResult = Optional.of(sides);
        when(sidesRepo.findBySideName((String) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sides/{name}", "Name");
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"sideName\":\"Side Name\",\"sideType\":\"Side Type\",\"sidePrice\":1,\"sideCount\":1,\"sideDesc\":\"Side"
                                        + " Desc\",\"imgURL\":\"https://example.org/example\"}"));
    }

    
    @Test
    void testGetallSides() throws Exception {
        when(sidesRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sides/all");
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallSides2() throws Exception {
        when(sidesRepo.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/sides/all");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallSidesOrderByPopularity() throws Exception {
        when(sidesRepo.findAll((Sort) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sides/all/orderByPopularity");
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

  
    @Test
    void testGetallSidesOrderByPopularity2() throws Exception {
        when(sidesRepo.findAll((Sort) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/sides/all/orderByPopularity");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallSidesOrderbyPrice() throws Exception {
        when(sidesRepo.findAll((Sort) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/sides/all/orderbyPrice");
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testGetallSidesOrderbyPrice2() throws Exception {
        when(sidesRepo.findAll((Sort) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/sides/all/orderbyPrice");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    
    @Test
    void testUpdateSides() throws Exception {
        Sides sides = new Sides();
        sides.setId(1L);
        sides.setImgURL("https://example.org/example");
        sides.setSideCount(1L);
        sides.setSideDesc("Side Desc");
        sides.setSideName("Side Name");
        sides.setSidePrice(1L);
        sides.setSideType("Side Type");
        when(sidesRepo.save((Sides) any())).thenReturn(sides);

        Sides sides1 = new Sides();
        sides1.setId(1L);
        sides1.setImgURL("https://example.org/example");
        sides1.setSideCount(1L);
        sides1.setSideDesc("Side Desc");
        sides1.setSideName("Side Name");
        sides1.setSidePrice(1L);
        sides1.setSideType("Side Type");
        String content = (new ObjectMapper()).writeValueAsString(sides1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/sides/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(sidesController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"sideName\":\"Side Name\",\"sideType\":\"Side Type\",\"sidePrice\":1,\"sideCount\":1,\"sideDesc\":\"Side"
                                        + " Desc\",\"imgURL\":\"https://example.org/example\"}"));
    }
}

