package com.product.model;
import java.sql.Blob;
import java.sql.Date;

public class ProductVO implements java.io.Serializable{
	private Integer proNo;
	private String productName;
	private String category;
	private Integer price;
	private byte[] image_1;
	private byte[] image_2;
	private String image_3;
	private Integer quantity;
	private Integer minimumQuantity;
	private Integer status;
	private String keyword;
	private String description;
	private String relatedProducts;
	private Integer priority;
	private Double discount;
	private Integer score;
	
	public Integer getProNo() {
		return proNo;
	}
	public void setProNo(Integer proNo) {
		this.proNo = proNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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

	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getMinimumQuantity() {
		return minimumQuantity;
	}
	public void setMinimumQuantity(Integer minimumQuantity) {
		this.minimumQuantity = minimumQuantity;
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
	public byte[] getImage_1() {
		return image_1;
	}
	public void setImage_1(byte[] image_1) {
		this.image_1 = image_1;
	}
	public byte[] getImage_2() {
		return image_2;
	}
	public void setImage_2(byte[] image_2) {
		this.image_2 = image_2;
	}
	public String getImage_3() {
		return image_3;
	}
	public void setImage_3(String image_3) {
		this.image_3 = image_3;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public void setScorel(Integer score) {
		this.score = score;
	}
	
}
