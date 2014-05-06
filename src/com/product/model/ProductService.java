package com.product.model;
import java.util.*;
public class ProductService {
	private ProductDAO_Interface dao;
	public ProductService(){
		dao = new ProductDAO();
	}
	public ProductVO addProduct(Integer prono, String productname, String category, Integer price, byte[] image1, byte[] image2, String image3, Integer quantity, Integer minimumquantity, Integer status, String keyword, String description, String relatedProducts, Integer priority, Double discount, Integer score){
		ProductVO productVO = new ProductVO();
		productVO.setProno(prono);
		
		return productVO;
	}
	public ProductVO updateProduct(Integer prono, String productname, String category, Integer price, byte[] image1, byte[] image2, String image3, Integer quantity, Integer minimumquantity, Integer status, String keyword, String description, String relatedProducts, Integer priority, Double discount, Integer score){
		ProductVO productVO = new ProductVO();
		
		return productVO;
	}
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	public ProductVO getOneProduct(int prono){
		return dao.findByPrimaryKey(prono);
	}
	public void deleteProduct(int prono){
		dao.delete(prono);
	}
}
