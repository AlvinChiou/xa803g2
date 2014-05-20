package com.product.model;
import java.sql.Blob;
import java.sql.Date;

public class ProductVO implements java.io.Serializable{
	private Integer prono;
	private String productname; //can null
	private String category; //can null
	private Integer price; //can null
	private byte[] image1; //can null
	private byte[] image2; //can null
	private byte[] image3; //can null
	private Integer quantity;
	private Integer minimumquantity;
	private Integer status;
	private String keyword; //can null	
	private String relatedProducts; //can null
	private Integer priority;
	private Double discount;
	private Integer score;
	private String description;
	public Integer getProno() {
		return prono;
	}
	public void setProno(Integer prono) {
		this.prono = prono;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public byte[] getImage1() {
		return image1;
	}
	public void setImage1(byte[] image1) {
		this.image1 = image1;
	}
	public byte[] getImage2() {
		return image2;
	}
	public void setImage2(byte[] image2) {
		this.image2 = image2;
	}
	public byte[] getImage3() {
		return image3;
	}
	public void setImage3(byte[] image3) {
		this.image3 = image3;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getMinimumquantity() {
		return minimumquantity;
	}
	public void setMinimumquantity(Integer minimumquantity) {
		this.minimumquantity = minimumquantity;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getRelatedProducts() {
		return relatedProducts;
	}
	public void setRelatedProducts(String relatedProducts) {
		this.relatedProducts = relatedProducts;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
