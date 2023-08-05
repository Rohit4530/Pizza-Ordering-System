package com.beverages.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beverages.entity.Beverages;
import com.beverages.service.BeveragesService;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
@RestController
@RequestMapping("/")
public class BeveragesController {
	
	@Autowired
	private BeveragesService service;
	
	@GetMapping("beverages/all")
	ResponseEntity<List<Beverages>>getallSides(){
			List<Beverages>beverages=service.getAll();
			return new ResponseEntity<List<Beverages>>(beverages,HttpStatus.OK);
	}
	
	@GetMapping("beverages/all/orderbyPrice")
	ResponseEntity<List<Beverages>>getallSidesOrderbyPrice(){
			List<Beverages>beverages=service.getAllSortedByPrice();
				return new ResponseEntity<List<Beverages>>(beverages,HttpStatus.OK);
	}

	@GetMapping("beverages/all/orderByPopularity")
	ResponseEntity<List<Beverages>>getallBeveragesOrderByPopularity(){
			List<Beverages>beverages=service.getAllSortedByOrderCount();
				return new ResponseEntity<List<Beverages>>(beverages,HttpStatus.OK);
	}
	@GetMapping("beverages/{name}")
	public ResponseEntity<Optional<Beverages>>getBeveragesByName(@PathVariable String  name){
			Optional<Beverages>gotBeverages=service.findByBeverageName(name);
				return new ResponseEntity<>(gotBeverages,HttpStatus.OK);	
	}
	@DeleteMapping("beverages/delete/{id}")
	public ResponseEntity<?>deleteBeverages(@PathVariable("id") Long id){
			service.deletebyId(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	@PutMapping("beverages/update")
	public ResponseEntity<Beverages>updateSides(@RequestBody Beverages beverages){
			return new ResponseEntity<Beverages>(service.updateBeverages(beverages),HttpStatus.ACCEPTED);
	}
   @PostMapping("beverages/add")
   public ResponseEntity<Beverages>addBeverages(@RequestBody Beverages beverages){
			service.createBeverages(beverages);
			return new ResponseEntity<Beverages>(beverages,HttpStatus.CREATED);
   }
   @GetMapping("beverage/find/{id}")
   public ResponseEntity<Beverages>getBevById(@PathVariable Long id){
	  Beverages bev= service.findBevById(id);
	   return new ResponseEntity<Beverages>(bev,HttpStatus.OK);
   }
}
