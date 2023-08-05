package com.pizza.home.services;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.pizza.home.model.Pizza;
import com.pizza.home.repo.PizzaRepository;
import com.pizza.home.services.exception.PizzaNotFoundException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PizzaServices.class})
@ExtendWith(SpringExtension.class)
class PizzaServicesTest {
    @MockBean
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaServices pizzaServices;

    
    @Test
    void testFindByPizzaName() {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        Optional<Pizza> ofResult = Optional.of(pizza);
        when(pizzaRepository.findByPizzaName((String) any())).thenReturn(ofResult);
        Optional<Pizza> actualFindByPizzaNameResult = pizzaServices.findByPizzaName("Name");
        assertSame(ofResult, actualFindByPizzaNameResult);
        assertTrue(actualFindByPizzaNameResult.isPresent());
        verify(pizzaRepository).findByPizzaName((String) any());
    }

    
    @Test
    void testFindByPizzaName2() {
        when(pizzaRepository.findByPizzaName((String) any())).thenReturn(Optional.empty());
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.findByPizzaName("Name"));
        verify(pizzaRepository).findByPizzaName((String) any());
    }

    
    @Test
    void testFindByPizzaName3() {
        when(pizzaRepository.findByPizzaName((String) any())).thenThrow(new PizzaNotFoundException("Pizza with name "));
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.findByPizzaName("Name"));
        verify(pizzaRepository).findByPizzaName((String) any());
    }

    
    @Test
    void testDeleteById() {
        doNothing().when(pizzaRepository).deleteById((Integer) any());
        pizzaServices.deleteById(1);
        verify(pizzaRepository).deleteById((Integer) any());
        assertTrue(pizzaServices.getAll().isEmpty());
    }

    
    @Test
    void testDeleteById2() {
        doThrow(new PizzaNotFoundException("Msg")).when(pizzaRepository).deleteById((Integer) any());
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.deleteById(1));
        verify(pizzaRepository).deleteById((Integer) any());
    }

    
    @Test
    void testUpdatePizza() {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        Optional<Pizza> ofResult = Optional.of(pizza);

        Pizza pizza1 = new Pizza();
        pizza1.setId(1);
        pizza1.setImgURL("https://example.org/example");
        pizza1.setPizzaCount(3L);
        pizza1.setPizzaDesc("Pizza Desc");
        pizza1.setPizzaName("Pizza Name");
        pizza1.setPizzaPrice(10.0d);
        pizza1.setPizzaType("Pizza Type");
        when(pizzaRepository.save((Pizza) any())).thenReturn(pizza1);
        when(pizzaRepository.findById((Integer) any())).thenReturn(ofResult);

        Pizza pizza2 = new Pizza();
        pizza2.setId(1);
        pizza2.setImgURL("https://example.org/example");
        pizza2.setPizzaCount(3L);
        pizza2.setPizzaDesc("Pizza Desc");
        pizza2.setPizzaName("Pizza Name");
        pizza2.setPizzaPrice(10.0d);
        pizza2.setPizzaType("Pizza Type");
        assertSame(pizza1, pizzaServices.updatePizza(pizza2));
        verify(pizzaRepository).save((Pizza) any());
        verify(pizzaRepository).findById((Integer) any());
    }

    
    @Test
    void testUpdatePizza2() {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        Optional<Pizza> ofResult = Optional.of(pizza);
        when(pizzaRepository.save((Pizza) any())).thenThrow(new PizzaNotFoundException("Msg"));
        when(pizzaRepository.findById((Integer) any())).thenReturn(ofResult);

        Pizza pizza1 = new Pizza();
        pizza1.setId(1);
        pizza1.setImgURL("https://example.org/example");
        pizza1.setPizzaCount(3L);
        pizza1.setPizzaDesc("Pizza Desc");
        pizza1.setPizzaName("Pizza Name");
        pizza1.setPizzaPrice(10.0d);
        pizza1.setPizzaType("Pizza Type");
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.updatePizza(pizza1));
        verify(pizzaRepository).save((Pizza) any());
        verify(pizzaRepository).findById((Integer) any());
    }

    
    

    
    @Test
    void testUpdatePizza4() {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        when(pizzaRepository.save((Pizza) any())).thenReturn(pizza);
        when(pizzaRepository.findById((Integer) any())).thenReturn(Optional.empty());

        Pizza pizza1 = new Pizza();
        pizza1.setId(1);
        pizza1.setImgURL("https://example.org/example");
        pizza1.setPizzaCount(3L);
        pizza1.setPizzaDesc("Pizza Desc");
        pizza1.setPizzaName("Pizza Name");
        pizza1.setPizzaPrice(10.0d);
        pizza1.setPizzaType("Pizza Type");
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.updatePizza(pizza1));
        verify(pizzaRepository).findById((Integer) any());
    }

    
    @Test
    void testCreatePizza() {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        when(pizzaRepository.save((Pizza) any())).thenReturn(pizza);

        Pizza pizza1 = new Pizza();
        pizza1.setId(1);
        pizza1.setImgURL("https://example.org/example");
        pizza1.setPizzaCount(3L);
        pizza1.setPizzaDesc("Pizza Desc");
        pizza1.setPizzaName("Pizza Name");
        pizza1.setPizzaPrice(10.0d);
        pizza1.setPizzaType("Pizza Type");
        assertSame(pizza, pizzaServices.createPizza(pizza1));
        verify(pizzaRepository).save((Pizza) any());
    }

    
    @Test
    void testCreatePizza2() {
        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        when(pizzaRepository.save((Pizza) any())).thenReturn(pizza);

        Pizza pizza1 = new Pizza();
        pizza1.setId(null);
        pizza1.setImgURL("https://example.org/example");
        pizza1.setPizzaCount(3L);
        pizza1.setPizzaDesc("Pizza Desc");
        pizza1.setPizzaName("Pizza Name");
        pizza1.setPizzaPrice(10.0d);
        pizza1.setPizzaType("Pizza Type");
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.createPizza(pizza1));
    }

    
    @Test
    void testCreatePizza3() {
        when(pizzaRepository.save((Pizza) any())).thenThrow(new PizzaNotFoundException("Msg"));

        Pizza pizza = new Pizza();
        pizza.setId(1);
        pizza.setImgURL("https://example.org/example");
        pizza.setPizzaCount(3L);
        pizza.setPizzaDesc("Pizza Desc");
        pizza.setPizzaName("Pizza Name");
        pizza.setPizzaPrice(10.0d);
        pizza.setPizzaType("Pizza Type");
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.createPizza(pizza));
        verify(pizzaRepository).save((Pizza) any());
    }

    
    @Test
    void testGetAllSortedByPrice() {
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        when(pizzaRepository.findAll((Sort) any())).thenReturn(pizzaList);
        List<Pizza> actualAllSortedByPrice = pizzaServices.getAllSortedByPrice();
        assertSame(pizzaList, actualAllSortedByPrice);
        assertTrue(actualAllSortedByPrice.isEmpty());
        verify(pizzaRepository).findAll((Sort) any());
    }

    
    @Test
    void testGetAllSortedByPrice2() {
        when(pizzaRepository.findAll((Sort) any())).thenThrow(new PizzaNotFoundException("pizzaPrice"));
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.getAllSortedByPrice());
        verify(pizzaRepository).findAll((Sort) any());
    }

    
    @Test
    void testGetAllSortedByOrderCount() {
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        when(pizzaRepository.findAll((Sort) any())).thenReturn(pizzaList);
        List<Pizza> actualAllSortedByOrderCount = pizzaServices.getAllSortedByOrderCount();
        assertSame(pizzaList, actualAllSortedByOrderCount);
        assertTrue(actualAllSortedByOrderCount.isEmpty());
        verify(pizzaRepository).findAll((Sort) any());
    }

    
    @Test
    void testGetAllSortedByOrderCount2() {
        when(pizzaRepository.findAll((Sort) any())).thenThrow(new PizzaNotFoundException("pizzaCount"));
        assertThrows(PizzaNotFoundException.class, () -> pizzaServices.getAllSortedByOrderCount());
        verify(pizzaRepository).findAll((Sort) any());
    }
}

