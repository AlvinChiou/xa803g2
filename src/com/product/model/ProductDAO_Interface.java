package com.product.model;
import java.util.*;
public interface ProductDAO_Interface {
	public int insert(ProductVO productVO);
	public int update(ProductVO productVO);
	public int delete(Integer proNo);
	public ProductVO findByPrimaryKey(Integer proNo);
	public List<ProductVO> getAll();
}
