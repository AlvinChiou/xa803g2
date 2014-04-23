package com.productitem.model;

import java.util.List;

import com.order.model.OrderVO;

public interface ProdItemDAO_interface {
	public int insert(ProdItemVO prodItemVO);
	public int update(ProdItemVO prodItemVO);
	public int delete(Integer itemNo);
	public ProdItemVO findByPrimaryKey(Integer itemNo);
	public List<ProdItemVO> getAll();
}
