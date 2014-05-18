package com.order.model;

import java.util.*;

import com.productitem.model.ProdItemVO;

public class OrderService {
	
	private OrderDAO_Interface dao;
	
	public OrderService(){
		dao = new OrderDAO();
	}
	
	public OrderVO addOrder(String ordaddr, String ordtel, java.sql.Timestamp ordgotime, java.sql.Timestamp ordarrtime, java.sql.Timestamp orddeltime, Integer ordstate, String memno, Integer empno){
		
		OrderVO orderVO = new OrderVO();
		orderVO.setOrdaddr(ordaddr);
		orderVO.setOrdtel(ordtel);
		orderVO.setOrdgotime(ordgotime);
		orderVO.setOrdarrtime(ordarrtime);
		orderVO.setOrddeltime(orddeltime);
		orderVO.setOrdstate(ordstate);
		orderVO.setMemno(memno);
		orderVO.setEmpno(empno);
		dao.insert(orderVO);
		return orderVO;
	}
	
	public OrderVO updateOrder(String ordno, java.sql.Timestamp ordtime, String ordaddr, String ordtel, java.sql.Timestamp ordgotime, java.sql.Timestamp ordarrtime, java.sql.Timestamp orddeltime, Integer ordstate, String memno, Integer empno){
		OrderVO orderVO = new OrderVO();
		orderVO.setOrdno(ordno);
		orderVO.setOrdtime(ordtime);
		orderVO.setOrdaddr(ordaddr);
		orderVO.setOrdtel(ordtel);
		orderVO.setOrdgotime(ordgotime);
		orderVO.setOrdarrtime(ordarrtime);
		orderVO.setOrddeltime(orddeltime);
		orderVO.setOrdstate(ordstate);
		orderVO.setMemno(memno);
		orderVO.setEmpno(empno);
		dao.update(orderVO);
		return orderVO;
	}
		
	public List<OrderVO> getAll(){
		return dao.getAll();
	}
	
	public OrderVO getOneOrder(String ordno){
		return dao.findByPrimaryKey(ordno);
	}
		
	public void deleteOrder(String ordno){
		dao.delete(ordno);
	}
	public void addOrdersByGenKey(OrderVO orderVO, Vector<ProdItemVO> list){
		dao.insertWithOrderItems(orderVO, list);
	}
	
}
