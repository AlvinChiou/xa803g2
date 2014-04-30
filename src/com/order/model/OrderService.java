package com.order.model;
import java.util.List;

public class OrderService {
	private OrderDAO_Interface dao;
	
	public OrderService(){
		dao = new OrderDAO();
	}
}
