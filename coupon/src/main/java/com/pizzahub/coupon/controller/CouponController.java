package com.pizzahub.coupon.controller;

import java.util.List;

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


import com.pizzahub.coupon.entity.Coupon;
import com.pizzahub.coupon.service.CouponService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class CouponController {
	@Autowired
	private CouponService couponService;

	@GetMapping("/coupons")
	ResponseEntity<List<Coupon>>getallCoupons(){
			List<Coupon>coupon=couponService.getAll();
				return new ResponseEntity<List<Coupon>>(coupon,HttpStatus.OK);
	}
	
	@GetMapping("/coupon/{couponName}")
	public ResponseEntity<Coupon> getCouponByName(@PathVariable String couponName){
		
			Coupon coupon = couponService.findByCouponName(couponName).get();
			
				return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
			
	}
	
	@GetMapping("/coupon/get/{couponId}")
	public ResponseEntity<Coupon> getCouponById(@PathVariable Long couponId){
	
			Coupon coupon = couponService.findByCouponId(couponId).get();
			
				return new ResponseEntity<Coupon>(coupon, HttpStatus.OK);
			
	}
	
	
	@PostMapping("/coupon/add")
	public ResponseEntity<Coupon> addCoupon(@RequestBody Coupon coupon){
		
					couponService.createCoupon(coupon);
					return new ResponseEntity<Coupon>(coupon,HttpStatus.CREATED);
				
		   }
		
	
	@GetMapping("/coupon/getApplicable/{orderAmount}")
	public ResponseEntity<List<Coupon>> getApplicableCoupons(@PathVariable Long orderAmount){
			List<Coupon>coupon=couponService.getApplicableCoupons(orderAmount);
		
				return new ResponseEntity<List<Coupon>>(coupon,HttpStatus.OK);
	}
	
	@DeleteMapping("/coupon/delete/{id}")
	public ResponseEntity<?>deleteCoupon(@PathVariable("id") Long id){
		
			couponService.deletebyId(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PutMapping("/coupon/update")
	public ResponseEntity<Coupon>updateCoupon(@RequestBody Coupon coupon){
		
			couponService.updateCoupon(coupon);
			return new ResponseEntity<>(coupon, HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/coupon/applyCoupon/{id}")
	public ResponseEntity<Long> applyCoupon(@PathVariable Long id){
		
			
			
				return new ResponseEntity<Long>(couponService.applyCoupon(id),HttpStatus.OK);
			
			
		
		
	}
	
}
