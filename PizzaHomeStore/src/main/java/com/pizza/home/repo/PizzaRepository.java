package com.pizza.home.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.home.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{

	Optional<Pizza> findByPizzaName(String name);

	

}
