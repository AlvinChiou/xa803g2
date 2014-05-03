package com.productitem.model;

import java.util.List;

import com.order.model.OrderVO;

public interface ProdItemDAO_interface {
	public int insert(ProdItemVO prodItemVO);
	public int update(ProdItemVO prodItemVO);
	public int delete(Integer itemno);
	public ProdItemVO findByPrimaryKey(Integer itemno);
	public List<ProdItemVO> getAll();
}
