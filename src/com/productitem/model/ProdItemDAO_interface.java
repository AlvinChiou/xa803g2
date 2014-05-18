package com.productitem.model;

import java.util.List;

import com.order.model.OrderVO;

public interface ProdItemDAO_interface {
	public void insert(ProdItemVO prodItemVO);
	public void update(ProdItemVO prodItemVO);
	public void delete(String ordno);	
	public ProdItemVO findByPrimaryKey(Integer itemno);
	public List<ProdItemVO> getAll();
	
	//同時新增訂單以及訂單項目
	public void insertByOrdNo(ProdItemVO prodItemVO, java.sql.Connection con);
}
