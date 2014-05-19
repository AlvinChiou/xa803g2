package com.product.model;
import java.util.*;
public interface ProductDAO_Interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(String prono);
	public ProductVO findByPrimaryKey(String prono);
	public List<ProductVO> getAll();
	//萬用複合查詢(傳入參數型態Map，回傳List)
	public List<ProductVO> getAll(Map<String, String[]> map);
}
