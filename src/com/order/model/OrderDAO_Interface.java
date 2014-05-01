package com.order.model;
import java.util.*;

public interface OrderDAO_Interface {
	public void insert(OrderVO orderVO);
	public void update(OrderVO orderVO);
	public void delete(String ordNo);
	public OrderVO findByPrimaryKey(String ordNo);
	public List<OrderVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//public List<OrderVO> getAll(Map<String, String[]> map);
	
	//查詢某個會員的訂單(一對多)
	//public Set<OrderVO> getOrderByMemNo(String memNo);
}
