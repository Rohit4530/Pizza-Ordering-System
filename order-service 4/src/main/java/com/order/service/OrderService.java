package com.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.order.service.exception.BadOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.entity.Order;
import com.order.entity.OrderCart;
import com.order.repo.OrderCartRepo;
import com.order.repo.OrderRepo;
import com.order.service.exception.DeleteFaliureException;
import com.order.service.exception.NoOrderWithUserIdException;
import com.order.service.exception.OrderUpdateFailedException;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private OrderCartRepo ordercartRepo;
	@Autowired
	private RestTemplate restTemplate;

	
	public List<Order> getAllOrders(){
		return orderRepo.findAll();
	}
	public Optional<Order> findOrderById(long id){
		return orderRepo.findById(id);
	}
	
	public void updateOrderStatus(long id, String status) {
		if(orderRepo.existsById(id)) {

			Order order = orderRepo.findById(id).get();
			order.setStatus(status);
			orderRepo.save(order);		
		}else {
			throw new OrderUpdateFailedException("Order cannot be updated as it does not exist");
		}
	}	
	public void deleteOrder(long id) {
		if(orderRepo.existsById(id))
			orderRepo.deleteById(id);
		else
			throw new DeleteFaliureException("Order cannot be deleted as it does not exist");
	}
	
	public Order addOrder(Order order) {
		if(order.getId()==null)
			return orderRepo.save(order);
		else
			throw new BadOrderException("Order cannot be added");
	}
	public List<Order> findOrderByUserId(Long userId) {
		if(userId!=null) {
			
			List<Order>orders =	orderRepo.findAll();
			List<Order>userOrders=new ArrayList<>();
			for(Order order:orders) {
				if(order.getUserId()==userId) 
					userOrders.add(order);
			    }
				return userOrders;
		}else {
			throw new NoOrderWithUserIdException("There is no order with userId " +userId);
		}
	
	}
	public boolean existById(Long id) {
		return orderRepo.existsById(id);
	}
	public List<OrderCart> getOrderCartByOrderId(Long id) {
		
		return ordercartRepo.findByOrders(id);
	}
	public List<OrderCart> getCardsByUserId(Long userId) {
		List USER_CART= restTemplate.getForEntity("http://localhost:9001/cart/products/"+userId,List.class).getBody();
		//List<OrderCart>userCart=ordercartRepo.findByUserId(userId);
		return USER_CART;
	}
}
