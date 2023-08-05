package com.pizza.home.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.pizza.home.model.Pizza;
import com.pizza.home.repo.PizzaRepository;
import com.pizza.home.services.exception.PizzaNotFoundException;
@Service
public class PizzaServices {
	@Autowired
	private PizzaRepository pizzaRepository;
	
	public List<Pizza> getAll() {
	  return pizzaRepository.findAll();
	}
    public Optional<Pizza>findByPizzaName(String name){
    	
    	 Optional<Pizza> pizza=pizzaRepository.findByPizzaName(name);
    	if(pizza.isPresent()) {
    		return pizza;
    	}else {
    		throw new PizzaNotFoundException("Pizza with name "+ name+" not Found");
    	}
    	
    }
   public void deleteById(Integer id) {
	   try{
		   pizzaRepository.deleteById(id);
	   }catch (Exception e) {
		   throw new PizzaNotFoundException("Pizza with Id "+ id +" not Found");
	}
   }
   public Pizza updatePizza(Pizza pizza) {
//	   Pizza getPizza = pizzaRepository.findById(pizza.getId()).orElseThrow(()-> new PizzaNotFoundException("Pizza not Found with ID provide"));
//	   if(getPizza != null) {
		   
//		 Pizza getPizza=pizzaRepository.save(pizza);
//	 }
//	   return getPizza;


	   return pizzaRepository.save(pizza);
   }
   
   public Pizza createPizza(Pizza pizza) {
	   if(pizza.getId()!=null) {
		   return pizzaRepository.save(pizza);
	   }else {
		   throw  new PizzaNotFoundException("Pizza is null");
	   }
   }
   public List<Pizza> getAllSortedByPrice() {
		  return pizzaRepository.findAll(Sort.by(Sort.Direction.ASC,"pizzaPrice"));
		}
   public List<Pizza> getAllSortedByOrderCount() {
		  return pizzaRepository.findAll(Sort.by(Sort.Direction.ASC,"pizzaCount"));
		}
  public Pizza findPizzaById(Integer id) {
	
	return pizzaRepository.findById(id).orElseThrow(()->new PizzaNotFoundException("Pizza with Id "+ id +" not Found"));
}
   
}
