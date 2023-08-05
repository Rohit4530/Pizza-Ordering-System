package com.pizzahub.coupon.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pizzahub.coupon.entity.Coupon;



public interface CouponRepo extends JpaRepository<Coupon, Long>{
	Optional<Coupon> findByCouponName(String name);
	
	
	List<Coupon> findByMinOrderAmountLessThanEqual(Long orderAmount);

}

//public interface BeveragesRepo extends JpaRepository<Beverages, Long>  {
//
//	Optional<Beverages> findByBeverageName(String name);
//	
//}