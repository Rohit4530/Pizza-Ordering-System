package com.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.order.entity.OrderCart;
import java.util.*;

public interface OrderCartRepo extends JpaRepository<OrderCart,Long> {
	@Query(value="SELECT * FROM orderdb.order_cart where orders=1;",nativeQuery = true)
	List<OrderCart> findByOrders(Long id);
	
	List<OrderCart> findByUserId(Long userId);

}
