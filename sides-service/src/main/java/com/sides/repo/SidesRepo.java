package com.sides.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sides.entity.Sides;

public interface SidesRepo extends JpaRepository<Sides, Long> {

	Optional<Sides> findBySideName(String name);

	void deleteById(Integer id);

}
