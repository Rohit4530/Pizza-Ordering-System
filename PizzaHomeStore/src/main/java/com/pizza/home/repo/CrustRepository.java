package com.pizza.home.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizza.home.model.Crust;
import com.pizza.home.model.Pizza;

public interface CrustRepository extends JpaRepository<Crust, Integer>{
}
