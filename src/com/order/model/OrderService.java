package com.order.model;

import java.util.*;

public class OrderService {
	
	private OrderDAO_Interface dao;
	
	public OrderService(){
		dao = new OrderDAO();
	}
	
	public OrderVO addOrder(String ord_Addr, String ord_Tel, java.sql.Timestamp ord_GOTime, java.sql.Timestamp ord_ArrTime, java.sql.Timestamp ord_DelTime, Integer ord_State, String mem_No, Integer empNo){
		
		OrderVO orderVO = new OrderVO();
		orderVO.setOrd_Addr(ord_Addr);
		orderVO.setOrd_Tel(ord_Tel);
		orderVO.setOrd_GOTime(ord_GOTime);
		orderVO.setOrd_ArrTime(ord_ArrTime);
		orderVO.setOrd_DelTime(ord_DelTime);
		orderVO.setOrd_State(ord_State);
		orderVO.setMem_No(mem_No);
		orderVO.setEmpNo(empNo);
		dao.insert(orderVO);
		return orderVO;
	}
	
	public OrderVO updateOrder(String ord_No, java.sql.Timestamp ord_Time, String ord_Addr, String ord_Tel, java.sql.Timestamp ord_GOTime, java.sql.Timestamp ord_ArrTime, java.sql.Timestamp ord_DelTime, Integer ord_State, String mem_No, Integer empNo){
		OrderVO orderVO = new OrderVO();
		orderVO.setOrd_No(ord_No);
		orderVO.setOrd_Time(ord_Time);
		orderVO.setOrd_Addr(ord_Addr);
		orderVO.setOrd_Tel(ord_Tel);
		orderVO.setOrd_GOTime(ord_GOTime);
		orderVO.setOrd_ArrTime(ord_ArrTime);
		orderVO.setOrd_DelTime(ord_DelTime);
		orderVO.setOrd_State(ord_State);
		orderVO.setMem_No(mem_No);
		orderVO.setEmpNo(empNo);
		dao.update(orderVO);
		return orderVO;
	}
		
	public List<OrderVO> getAll(){
		return dao.getAll();
	}
	
	public OrderVO getOneOrder(String ordNo){
		return dao.findByPrimaryKey(ordNo);
	}
		
	public void deleteOrder(String ordNo){
		dao.delete(ordNo);
	}
	
}
