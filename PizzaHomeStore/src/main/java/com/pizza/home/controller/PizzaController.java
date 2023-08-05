package com.pizza.home.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pizza.home.model.Pizza;
import com.pizza.home.services.PizzaServices;

//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
@RestController
@RequestMapping("/pizza")
public class PizzaController {
	@Autowired
	private PizzaServices services;
	@GetMapping("/all")
	ResponseEntity<List<Pizza>>getallPizza(){
			List<Pizza>pizzas=services.getAll();
		return new ResponseEntity<List<Pizza>>(pizzas,HttpStatus.OK);
	}
	@GetMapping("/all/orderByPrice")
	ResponseEntity<List<Pizza>>getallPizzaOrderByPrice(){
			List<Pizza>pizzas=services.getAllSortedByPrice();	
			return new ResponseEntity<List<Pizza>>(pizzas,HttpStatus.OK);	
	}
	@GetMapping("/all/orderByPopularity")
	ResponseEntity<List<Pizza>>getallPizzaOrderByPopularity(){	
			List<Pizza>pizzas=services.getAllSortedByOrderCount();
			return new ResponseEntity<List<Pizza>>(pizzas,HttpStatus.OK);
	}
	@GetMapping("/{name}")
	public ResponseEntity<Optional<Pizza>>getPizzaByName(@PathVariable String  name){	
			Optional<Pizza>gotPizza=services.findByPizzaName(name);
			return new ResponseEntity<>(gotPizza,HttpStatus.OK);		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?>deletePizza(@PathVariable Integer id){
			services.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	@PutMapping("/update")
	public ResponseEntity<Pizza>updatePizza(@RequestBody Pizza pizza){
			services.updatePizza(pizza);
			return new ResponseEntity<Pizza>(HttpStatus.ACCEPTED);
	}
   @PostMapping("/add")
   public ResponseEntity<Pizza>addPizza(@RequestBody Pizza pizza){
			services.createPizza(pizza);
			return new ResponseEntity<Pizza>(pizza,HttpStatus.CREATED);
   }
   @GetMapping("/find/{id}")
   public ResponseEntity<Pizza>getPizzaById(@PathVariable Integer id){
	  Pizza pizza= services.findPizzaById(id);
	  return new ResponseEntity<Pizza>(pizza,HttpStatus.OK);
   }
   @PostMapping(value="/upload-image")
   public ResponseEntity<String> handleFileUpload(@RequestParam("image") MultipartFile file) {
	   String fileName = file.getOriginalFilename();

   String filePath = "/Users/RO20423171/Desktop/PizzaAuthFront/src/assets/img/" + fileName;
   String filePathAtDB= "../../assets/img/" + fileName;
   try {
	  
	   file.transferTo(new File(filePath));
	   
	   } catch (IOException e) {
		   return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	 }
   return new ResponseEntity<String>(filePathAtDB, HttpStatus.OK);
   }
}
