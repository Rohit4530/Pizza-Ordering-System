package com.sides.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.sides.entity.Sides;
import com.sides.service.SidesService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600,allowCredentials = "true",allowedHeaders = "*")
@RequestMapping("/sides")
public class SidesController {
 
	@Autowired
	private SidesService service;
	
	@GetMapping("/all")
	ResponseEntity<List<Sides>>getallSides(){	
			List<Sides>sides=service.getAll();
		return new ResponseEntity<List<Sides>>(sides,HttpStatus.OK);
	}
	@GetMapping("/all/orderbyPrice")
	ResponseEntity<List<Sides>>getallSidesOrderbyPrice(){	
			List<Sides>sides=service.getAllSortedByPrice();
				return new ResponseEntity<List<Sides>>(sides,HttpStatus.OK);
	}

	@GetMapping("/all/orderByPopularity")
	ResponseEntity<List<Sides>>getallSidesOrderByPopularity(){
			List<Sides>sides=service.getAllSortedByOrderCount();
				return new ResponseEntity<List<Sides>>(sides,HttpStatus.OK);
	}
	@GetMapping("/{name}")
	public ResponseEntity<Optional<Sides>>getSidesByName(@PathVariable String  name){
			Optional<Sides>gotSides=service.findBySideName(name);
				return new ResponseEntity<>(gotSides,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?>deleteSides(@PathVariable("id") Long id){
			service.deletebyId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PutMapping("/update")
	public ResponseEntity<Sides>updateSides(@RequestBody Sides sides){
			service.updateSides(sides);
			return new ResponseEntity<Sides>(sides,HttpStatus.ACCEPTED);
	}
   @PostMapping("/add")
   public ResponseEntity<Sides>addSides(@RequestBody Sides sides){ 
			service.createSides(sides);
			return new ResponseEntity<Sides>(sides,HttpStatus.CREATED);
    }
   @GetMapping("/find/{id}")
	public ResponseEntity<Optional<Sides>>getSidesById(@PathVariable Long id){
			Optional<Sides>gotSides=service.findBySideId(id);
				return new ResponseEntity<>(gotSides,HttpStatus.OK);
	}

}
