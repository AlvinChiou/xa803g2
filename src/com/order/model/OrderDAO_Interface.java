package com.order.model;
import java.util.List;

public interface OrderDAO_Interface {
	public void insert(OrderVO orderVO);
	public void update(OrderVO orderVO);
	public void delete(Integer ordNo);
	public OrderVO findByPrimaryKey(String ordNo);
	public List<OrderVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<OrderVO> getAll(Map<String, String[]> map);
}
