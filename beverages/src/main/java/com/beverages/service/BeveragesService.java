package com.beverages.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.beverages.entity.Beverages;
import com.beverages.repo.BeveragesRepo;
import com.beverages.service.exception.BeverageCreateException;
import com.beverages.service.exception.BeverageNotFoundException;
import com.beverages.service.exception.BevrageUpdateException;


@Service
public class BeveragesService {
	
	@Autowired
	private BeveragesRepo beveragesRepo;
	
	public List<Beverages> getAll(){
		return beveragesRepo.findAll();
	}
	public Optional<Beverages>findByBeverageName(String name){
		
	Optional<Beverages>bev= beveragesRepo.findByBeverageName(name);
	   if(bev.isPresent()) {
		    return bev;
	   }else {
		    throw new BeverageNotFoundException("Bevrage with name "+name+" not found");
	   }
	}
	public void deletebyId(Long id ) {
		Beverages bev=beveragesRepo.findById(id)
				.orElseThrow(()->
				new BeverageNotFoundException
				("Bevrage with Id "+id+" Cannot be deleted because it does not exist"));
		if(bev.getId()!=null)
		beveragesRepo.deleteById(id);
	}
	public Beverages updateBeverages(Beverages beverages) {

			return beveragesRepo.save(beverages);	

		
	}
	public Beverages createBeverages(Beverages beverages) {
		if(beverages.getBeverageName()!=null)
		return beveragesRepo.save(beverages);
		else
			throw new  BeverageCreateException("Bevrage cannot be created");
	}
	public List<Beverages> getAllSortedByPrice() {
		  return beveragesRepo.findAll(Sort.by(Sort.Direction.ASC,"beveragePrice"));
		}
 public List<Beverages> getAllSortedByOrderCount() {
		  return beveragesRepo.findAll(Sort.by(Sort.Direction.DESC,"beverageCount"));
		}
public Beverages findBevById(Long id) {
	return beveragesRepo.findById(id).orElseThrow(()->new BeverageNotFoundException("Bevrage with Id "+id+" not found"));
}

	
	
}
