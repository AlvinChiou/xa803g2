package com.productitem.model;

import java.util.List;

import com.order.model.OrderVO;

public interface ProdItemDAO_interface {
	public void insert(ProdItemVO prodItemVO);
	public void update(ProdItemVO prodItemVO);
	public void delete(Integer itemno);
	public ProdItemVO findByPrimaryKey(Integer itemno);
	public List<ProdItemVO> getAll();
}
