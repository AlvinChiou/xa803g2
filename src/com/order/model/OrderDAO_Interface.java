package com.order.model;
import java.util.*;

public interface OrderDAO_Interface {
	public void insert(OrderVO orderVO);
	public void update(OrderVO orderVO);
	public void delete(String ordNo);
	public OrderVO findByPrimaryKey(String ordNo);
	public List<OrderVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap)(�^�� List)
	//public List<OrderVO> getAll(Map<String, String[]> map);
	
	//�d�߬Y�ӷ|�����q��(�@��h)
	//public Set<OrderVO> getOrderByMemNo(String memNo);
}
