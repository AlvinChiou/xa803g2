package com.product.model;
import java.util.*;
public interface ProductDAO_Interface {
	public void insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public void delete(String prono);
	public ProductVO findByPrimaryKey(String prono);
	public List<ProductVO> getAll();
	//�U�νƦX�d��(�ǤJ�Ѽƫ��AMap�A�^��List)
	public List<ProductVO> getAll(Map<String, String[]> map);
}
