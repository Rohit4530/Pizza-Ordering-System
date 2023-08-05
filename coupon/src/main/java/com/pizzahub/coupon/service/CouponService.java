package com.pizzahub.coupon.service;

import org.springframework.stereotype.Service;


import com.pizzahub.coupon.entity.Coupon;
import com.pizzahub.coupon.repo.CouponRepo;
import com.pizzahub.coupon.service.exception.CouponNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

@Service
public class CouponService {
	
	@Autowired
	private CouponRepo couponRepo;
	
	
	
	private Coupon coupon;
		
		public List<Coupon> getAll(){
			return couponRepo.findAll();
		}
		public Optional<Coupon>findByCouponName(String name){
			Optional<Coupon>coup=couponRepo.findByCouponName(name);
			if(coup.isPresent()) {
				return coup;
			}else {
				throw new CouponNotFoundException("coupon with name "+name+" not found");
			}
			
		}
		
		
		public Optional<Coupon>findByCouponId(Long id){
			if(couponRepo.existsById(id))
			return couponRepo.findById(id);
			else
				throw new CouponNotFoundException("coupon with id "+id+" not found");
		}
		public void deletebyId(Long id ) {
			couponRepo.deleteById(id);
			if(couponRepo.existsById(id))
				couponRepo.deleteById(id);
				else
					throw new CouponNotFoundException("coupon with id "+id+" not found");
		}
		public Coupon updateCoupon(Coupon coupon) {
			return couponRepo.save(coupon);
		}
		public Coupon createCoupon(Coupon coupon) {
			return couponRepo.save(coupon);
		}
		
		public List<Coupon> getApplicableCoupons(Long orderAmount){
			return couponRepo.findByMinOrderAmountLessThanEqual(orderAmount);
		}
		
		
		
		public Long applyCoupon(Long id) {		
				
			Coupon coupon1= couponRepo.findById(id).get();
			return coupon1.getCouponPrice();
			
		}
}
