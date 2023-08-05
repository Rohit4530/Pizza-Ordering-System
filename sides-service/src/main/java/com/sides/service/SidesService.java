package com.sides.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sides.entity.Sides;
import com.sides.repo.SidesRepo;
import com.sides.service.exception.NullSideException;
import com.sides.service.exception.SideCannotUpdateException;
import com.sides.service.exception.SideNotFoundException;

@Service
public class SidesService {
	@Autowired
	private SidesRepo sidesRepo;
	
	public List<Sides> getAll(){
		return sidesRepo.findAll();
	}
	public Optional<Sides>findBySideName(String name){
		Optional<Sides> side=sidesRepo.findBySideName(name);
		if(side.isPresent())
		return side;
		else
			throw new SideNotFoundException("side with name "+name+" not Found");
	}
	public void deletebyId(Long id ) {
		Sides side=sidesRepo.findById(id).orElseThrow(()->new SideNotFoundException("side with Id "+id+" not Found"));
		sidesRepo.deleteById(id);
	}
	public Sides updateSides(Sides sides) {

			return sidesRepo.save(sides);

	}
	public Sides createSides(Sides sides) {
		if(sides.getSideName()!=null) {
			return sidesRepo.save(sides);
		}else {
			throw new  NullSideException("Cannot add null side");
		}
		
	}
	public List<Sides> getAllSortedByPrice() {
		  return sidesRepo.findAll(Sort.by(Sort.Direction.ASC,"sidePrice"));
		}
 public List<Sides> getAllSortedByOrderCount() {
		  return sidesRepo.findAll(Sort.by(Sort.Direction.DESC,"sideCount"));
		}
public Optional<Sides> findBySideId(Long id) {
	Optional<Sides> side=sidesRepo.findById(id);
	if(side.isPresent())
	return side;
	else
		throw new SideNotFoundException("side with name "+id+" not Found");
	
}
  

}
