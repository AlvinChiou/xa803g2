package com.order.model;
import java.util.*;

import com.productitem.controller.ProductItemServlet;
import com.productitem.model.ProdItemVO;

public interface OrderDAO_Interface {
	public void insert(OrderVO orderVO);
	public void update(OrderVO orderVO);
	public void delete(String ordno);
	public OrderVO findByPrimaryKey(String ordno);
	public List<OrderVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	//public List<OrderVO> getAll(Map<String, String[]> map);
	
	//查詢某個會員的訂單(一對多)
	//public Set<OrderVO> getOrderByMemNo(String memNo);
	public void insertWithOrderItems(OrderVO orderVO, Vector<ProdItemVO> list);
}
