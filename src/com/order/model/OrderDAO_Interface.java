package com.order.model;
import java.util.List;

public interface OrderDAO_Interface {
	public int insert(OrderVO orderVO);
	public int update(OrderVO orderVO);
	public int delete(Integer ordNo);
	public OrderVO findByPrimaryKey(Integer ordNo);
	public List<OrderVO> getAll();
}
