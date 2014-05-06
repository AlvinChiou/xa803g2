package com.product.model;
import java.util.*;
public interface ProductDAO_Interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(Integer prono);
	public ProductVO findByPrimaryKey(Integer prono);
	public List<ProductVO> getAll();
}
