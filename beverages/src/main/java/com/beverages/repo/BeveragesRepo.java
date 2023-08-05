package com.beverages.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beverages.entity.Beverages;

public interface BeveragesRepo extends JpaRepository<Beverages, Long>  {

	Optional<Beverages> findByBeverageName(String name);
	
}
